package com.example.cinema.vo;

import lombok.Data;

import java.util.Date;
@Data
public class RechargeVO {
    private Integer userId;
    private Date date;
    private double balance;
    public RechargeVO(Integer userId,Date date,double balance){
        this.userId=userId;
        this.balance=balance;
        this.date=date;
    }
}
