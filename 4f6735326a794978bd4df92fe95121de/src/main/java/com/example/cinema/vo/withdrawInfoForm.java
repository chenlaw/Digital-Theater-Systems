package com.example.cinema.vo;

import java.util.Date;

public class withdrawInfoForm {
    private String withdrawDescription;
    private int scheduleId;
    Date closeTime;
    double discount;

    public String getWithdrawDescription() {
        return withdrawDescription;
    }

    public void setWithdrawDescription(String withdrawDescription) {
        this.withdrawDescription = withdrawDescription;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
