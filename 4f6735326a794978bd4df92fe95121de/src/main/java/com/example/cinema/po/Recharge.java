package com.example.cinema.po;

import lombok.Data;

import java.util.Date;

public class Recharge {
    private double balance;
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
