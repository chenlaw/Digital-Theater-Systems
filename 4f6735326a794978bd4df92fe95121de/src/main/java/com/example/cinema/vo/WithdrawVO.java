package com.example.cinema.vo;

import java.util.Date;

public class WithdrawVO {

    private int id;

    private String withdrawDescription;

    private Date closeTime;

    private double discount;

    private String filmName;

    private Date filmStartTime;

    private double filmFare;

    private String hallName;

    private int hallId;

    private int scheduleId;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Date getFilmStartTime() {
        return filmStartTime;
    }

    public void setFilmStartTime(Date filmStartTime) {
        this.filmStartTime = filmStartTime;
    }

    public double getFilmFare() {
        return filmFare;
    }

    public void setFilmFare(double filmFare) {
        this.filmFare = filmFare;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getWithdrawDescription() {
        return withdrawDescription;
    }

    public void setWithdrawDescription(String withdrawDescription) {
        this.withdrawDescription = withdrawDescription;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
