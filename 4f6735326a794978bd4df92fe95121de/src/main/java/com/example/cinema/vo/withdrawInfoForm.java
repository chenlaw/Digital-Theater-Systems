package com.example.cinema.vo;

import java.util.Date;

/**
 * @author ttc
 */
public class withdrawInfoForm {

    /**
     * 退票描述
     */
    private String withdrawDescription;

    /**
     * 场次id
     */
    private int scheduleId;

    /**
     * 退票关闭时间
     */
    Date closeTime;

    /**
     * 退票折扣
     */
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
