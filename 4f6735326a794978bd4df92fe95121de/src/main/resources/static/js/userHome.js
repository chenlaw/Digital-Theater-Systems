$(document).ready(function () {

    getMovieList('');

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
                "        <a href='/user/movieDetail?id="+ movie.id + "' class='active'>购 票</a>" +
                "    </div>" +
                "    </div>" +
                "</dd>"
        })
        $('.movie-list').append(movieDomStr);
    }



})