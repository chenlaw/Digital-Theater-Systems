$(document).ready(function(){

    var movieId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var userId = sessionStorage.getItem('id');
    var isLike = false;

    getMovie();
    if(sessionStorage.getItem('role') === 'ticketSeller')
        getMovieLikeChart();

    function getMovieLikeChart() {
       getRequest(
           '/movie/' + movieId + '/like/date',
           function(res){
               var data = res.content,
                    dateArray = [],
                    numberArray = [];
               data.forEach(function (item) {
                   dateArray.push(item.likeTime);
                   numberArray.push(item.likeNum);
               });

               var myChart = echarts.init($("#like-date-chart")[0]);

               // 指定图表的配置项和数据
               var option = {
                   title: {
                       text: '想看人数变化表'
                   },
                   xAxis: {
                       type: 'category',
                       data: dateArray
                   },
                   yAxis: {
                       type: 'value'
                   },
                   series: [{
                       data: numberArray,
                       type: 'line'
                   }]
               };

               // 使用刚指定的配置项和数据显示图表。
               myChart.setOption(option);
           },
           function (error) {
               alert(error);
           }
       );
    }

    function getMovie() {
        getRequest(
            '/movie/'+movieId + '/' + sessionStorage.getItem("id"),
            function(res){
                var data = res.content;
                isLike = data.islike;
                repaintMovieDetail(data);
                repaintMovieDetailInput(data)
            },
            function (error) {
                alert(error);
            }
        );
    }

    function repaintMovieDetail(movie) {
        !isLike ? $('.icon-heart').removeClass('error-text') : $('.icon-heart').addClass('error-text');
        $('#like-btn span').text(isLike ? ' 已想看' : ' 想 看');
        $('#movie-img').attr('src',movie.posterUrl);
        $('#movie-name').text(movie.name);
        $('#order-movie-name').text(movie.name);
        $('#movie-description').text(movie.description);
        $('#movie-startDate').text(new Date(movie.startDate).toLocaleDateString());
        $('#movie-type').text(movie.type);
        $('#movie-country').text(movie.country);
        $('#movie-language').text(movie.language);
        $('#movie-director').text(movie.director);
        $('#movie-starring').text(movie.starring);
        $('#movie-writer').text(movie.screenWriter);
        $('#movie-length').text(movie.length);
    }

    // user界面才有
    $('#like-btn').click(function () {
        var url = isLike ?'/movie/'+ movieId +'/unlike?userId='+ userId :'/movie/'+ movieId +'/like?userId='+ userId;
        postRequest(
             url,
            null,
            function (res) {
                 isLike = !isLike;
                getMovie();
            },
            function (error) {
                alert(error);
            });
    });

    // admin界面才有
    $("#modify-btn").click(function () {
        //alert('交给你们啦，修改时需要在对应html文件添加表单然后获取用户输入，提交给后端，别忘记对用户输入进行验证。（可参照添加电影&添加排片&修改排片）');
        var movieForm=getMovieForm();
        if(!validateMovieForm(movieForm)){
            return;
        }
        postRequest(
           '/movie/update',
           movieForm,
           function (res) {
               $("#movieModal").modal('hide');
               getMovie();
               if(!res.success){
                   alert(res.message)
               }
           },
           function (error) {

           }
       )
    });
    //wph 5.22
    $("#delete-btn").click(function () {
        //alert('交给你们啦，下架别忘记需要一个确认提示框，也别忘记下架之后要对用户有所提示哦');
        var r=confirm("确认下架电影？")
        if(r){
            var formData = {movieIdList:[movieId]};
            postRequest(
                "/movie/off/batch",
                formData,
                function(){
                    window.location.href="/ticketSeller/movie/manage";
                },
                function(error){
                    alert(error);
                }
            )
        }
    });
    //wph 5.22
    function getMovieForm() {
        return {
            id: movieId,
            name: $('#movie-name-input').val(),
            startDate: $('#movie-date-input').val(),
            posterUrl: $('#movie-img-input').val(),
            description: $('#movie-description-input').val(),
            type: $('#movie-type-input').val(),
            length: $('#movie-length-input').val(),
            country: $('#movie-country-input').val(),
            starring: $('#movie-star-input').val(),
            director: $('#movie-director-input').val(),
            screenWriter: $('#movie-writer-input').val(),
            language: $('#movie-language-input').val()
        };
    }

    function validateMovieForm(data) {
        var isValidate = true;
        if(!data.name) {
            isValidate = false;
            $('#movie-name-input').parent('.form-group').addClass('has-error');
        }
        if(!data.posterUrl) {
            isValidate = false;
            $('#movie-img-input').parent('.form-group').addClass('has-error');
        }
        if(!data.startDate) {
            isValidate = false;
            $('#movie-date-input').parent('.form-group').addClass('has-error');
        }
        return isValidate;
    }

    function repaintMovieDetailInput(movie){
        $('#movie-img-input').val(movie.posterUrl);
        $('#movie-name-input').val(movie.name);
        $('#movie-description-input').val(movie.description);
        $('#movie-data-input').val(new Date(movie.startDate).toLocaleDateString());
        $('#movie-type-input').val(movie.type);
        $('#movie-country-input').val(movie.country);
        $('#movie-language-input').val(movie.language);
        $('#movie-director-input').val(movie.director);
        $('#movie-star-input').val(movie.starring);
        $('#movie-writer-input').val(movie.screenWriter);
        $('#movie-length-input').val(movie.length);
    }
});