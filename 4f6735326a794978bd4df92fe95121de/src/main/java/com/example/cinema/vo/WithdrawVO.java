package com.example.cinema.vo;

import java.util.Date;

/**
 * @author ttc
 */
public class WithdrawVO {

    /**
     * 退票信息id
     */
    private int id;

    /**
     * 退票描述
     */
    private String withdrawDescription;

    /**
     * 退票截止时间
     */
    private Date closeTime;

    /**
     * 退票折扣
     */
    private double discount;

    /**
     * 电影名称
     */
    private String filmName;

    /**
     * 电影开始时间
     */
    private Date filmStartTime;

    /**
     * 电影价格
     */
    private double filmFare;

    /**
     * 影厅名字
     */
    private String hallName;

    /**
     * 影厅id
     */
    private int hallId;

    /**
     * 场次id
     */
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
