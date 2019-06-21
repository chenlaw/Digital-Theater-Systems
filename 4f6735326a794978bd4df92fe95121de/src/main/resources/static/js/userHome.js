$(document).ready(function () {

    getMovieList('');
    getMovieRanking();

    function getMovieList(keyword) {
        getRequest(
            '/movie/search?keyword='+keyword,
            function (res) {
                renderMovieList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    function renderMovieList(list) {
        $('.movie-on-list').empty();
        var movieDomStr = '';
        if(list.length>5){
            list=list.slice(0,5)
        }
        list.forEach(function (movie) {
            movieDomStr+=
                "<dd>" +
                "    <div class='movie-item'>" +
                "        <a href='/user/movieDetail?id="+ movie.id +"'>" +
                "        <div class='movie-poster'>" +
                "        <img src='"+(movie.posterUrl || "../images/defaultAvatar.jpg")+"'>" +
                "        <div class='movie-overlay movie-overlay-bg'>" +
                "        <div class='movie-info'>" +
                "        <div class='movie-title movie-title-padding'>"+movie.name+"</div>" +
                "    </div>" +
                "    </div>" +
                "    </div>" +
                "    </a>" +
                "    <div class='movie-detail movie-detail-strong movie-sale'>" +
                // "<a href='/cinemas?movieId="+movie.id+"' class='active' target='_blank' data-act='salePlayingMovie-click' data-val='{movie.id:"+movie.id+"}'>购 票</a>"+
                "        <a href='/user/movieDetail?id="+ movie.id + "' class='active'>购 票</a>" +
                "    </div>" +
                "    </div>" +
                "</dd>"
        })
        $('.movie-list').append(movieDomStr);
    }

    function getMovieRanking(){
        getRequest(
            '/statistics/boxOffice/total',
            function (res) {
                renderMovieRanking(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    function getUrl(id){
        postRequest(
            '/movie/{id}',
            {id:id},
            function(res){
                return res.posterUrl;
            },
            function(error){
                alert(error);
        });
    }

    function renderMovieRanking(list){
        $('.panel-container').empty();
        var movieDomStr = '';
        if(list.length>5){
            list=list.slice(0,5)
        }
        var movieTop=list[0];
        movieDomStr +=
            "<ul class='ranking-wrapper ranking-box'>"+
            "   <li class='ranking-item ranking-top ranking-index-1'>"+
            "       <a href='/user/movieDetail?id="+ movieTop.movieId +"'>"+
            "           <div class='ranking-top-left'>"+
            "               <i class='ranking-top-icon'></i>"+
            "               <img class='ranking-img  default-img' src='"+(getUrl(movieTop.movieId) || "../images/defaultAvatar.jpg")+"'>"+
            "           </div>"+
            "           <div class='ranking-top-right'>"+
            "               <div class='ranking-top-right-main'>"+
            "                   <span class='ranking-top-moive-name'>"+movieTop.name+"</span>"+
            "                   <p class='ranking-top-wish'>"+
            "                       <span class='stonefont'>"+movieTop.boxOffice+"</span>"+
            "                   </p>"+
            "               </div>"+
            "           </div>"+
            "       </a>"+
            "   </li>"+
            "</ul>"

        $('.panel-container').append(movieDomStr);
    }

})