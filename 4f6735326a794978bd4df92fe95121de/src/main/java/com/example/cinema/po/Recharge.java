package com.example.cinema.po;

import lombok.Data;

import java.util.Date;

public class Recharge {
    /**
     * 消费金额
     */
    private double balance;
    /**
     * 消费金额
     */
    private Date time;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
