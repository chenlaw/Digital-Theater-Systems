package com.example.cinema.vo;


public class PopularMovieVO {
    private Integer movieId;
    private Double boxOffice;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBoxOffice() {
        return boxOffice;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setBoxOffice(Double boxOffice) {
        this.boxOffice = boxOffice;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
}
