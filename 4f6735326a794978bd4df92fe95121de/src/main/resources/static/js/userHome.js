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
        var i=0;
        var j=0
        list.forEach(function (movie) {
            if(i<=4&&movie.status==0&&(new Date(movie.startDate).getTime())<(new Date().getTime())){
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
                i+=1
            }
        })
        var movieDomStr1=''
        list.forEach(function (movie) {
            if(j<=4&&(new Date(movie.startDate).getTime())>(new Date().getTime())){
                movieDomStr1+=
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
                j+=1
            }
        })
        $('#movie-list1').append(movieDomStr);
        $('#movie-list2').append(movieDomStr1);
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
        getRequest(
            '/movie/id?id='+id,
            function(res){
                var url=res.content.posterUrl;
                return url
            },
            function(error){
                alert(error);
        });

    }

    function renderMovieRanking(list){
        $('#movie-ranking').empty();
        var movieTop=list[0];
        var url=getUrl(parseInt(movieTop.movieId))
        console.log(url)
        var movieDomStr =
            "<ul class='ranking-wrapper ranking-box'>"+
            "   <li class='ranking-item ranking-top ranking-index-1'>"+
            "       <a href='/user/movieDetail?id="+ movieTop.movieId +"'>"+
            "           <div class='ranking-top-left'>"+
            "               <i class='ranking-top-icon'></i>"+
            "               <img class='ranking-img  default-img' src='"+( url || "../images/defaultAvatar.jpg")+"'>"+
            "           </div>"+
            "           <div class='ranking-top-right'>"+
            "               <div class='ranking-top-right-main'>"+
            "                   <span class='ranking-top-moive-name'>"+movieTop.name+"</span>"+
            "                   <p class='ranking-top-wish'>"+
            "                       <span class='stonefont'>"+movieTop.boxOffice+"</span>元"+
            "                   </p>"+
            "               </div>"+
            "           </div>"+
            "       </a>"+
            "   </li>"
        var i=2;
        list.slice(1,10).forEach(function(movie){
            movieDomStr+=
                "<li class='ranking-item ranking-index-"+i+"'>" +
                "      <a href='/user/movieDetail?id="+ movie.movieId +"'>" +
                "          <span class='normal-link'>" +
                "            <i class='ranking-index'>"+i+"</i>" +
                "            <span class='ranking-movie-name'>"+movie.name+"</span>" +
                "            <span class='ranking-num-info'>" +
                "                <span class='stonefont'>"+movie.boxOffice+"</span>元" +
                "            </span>" +
                "          </span>" +
                "      </a>" +
                "    </li>"
            i+=1;
        })
        movieDomStr+=
            "</ul>"

        $('#movie-ranking').append(movieDomStr);
    }

})