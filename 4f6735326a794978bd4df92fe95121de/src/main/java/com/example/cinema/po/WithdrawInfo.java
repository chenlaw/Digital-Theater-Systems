package com.example.cinema.po;


import java.sql.Timestamp;
import java.util.Date;

public class WithdrawInfo {

    /**
     * 退票信息id
     */
    private int id;

    /**
     * 退票信息描述
     */
    private String withdrawDescription;

    /**
     * 退票截止时间
     */
    private Date closeTime;

    /**
     * 场次id
     */
    private int scheduleId;

    /**
     * 退票折扣
     */
    private double discount;

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

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
