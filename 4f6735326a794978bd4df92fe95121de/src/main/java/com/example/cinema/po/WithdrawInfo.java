package com.example.cinema.po;


import java.sql.Timestamp;
import java.util.Date;

public class WithdrawInfo {

    private int id;

    private String withdrawDescription;

    private Date closeTime;

    private int scheduleId;

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
