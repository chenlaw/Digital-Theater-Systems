package com.example.cinema.vo;


import lombok.Data;
import java.util.Date;

@Data
public class ConsumptionVO {
    private int userId;
    private double balance;
    private Date time;
    private String way;

    public ConsumptionVO(int userId, double balance, Date time, String way) {
        this.userId = userId;
        this.balance = balance;
        this.time = time;
        this.way = way;
    }
}
