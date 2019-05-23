package com.example.cinema.vo;

import com.example.cinema.po.Movie;


import java.util.Date;

public class MoviePlacingRateVO {
    private String name;
    private Movie movie;
    private Double rate;
    private Date date;

    public MoviePlacingRateVO(Movie movie,Date date,Double rate){
        this.movie=movie;
        this.name=movie.getName();
        this.date=date;
        this.rate=rate;
    }

    public String getName(){return movie.getName();}

    public Date getDate(){return date;}

    public Double getRate(){return rate;}

    public void setMovie(Movie movie){this.movie=movie;}

    public void setDate(Date date){this.date=date;}

    public void setRate(Double rate){this.rate=rate;}
}

