package com.example.cinema.po;

import lombok.Data;

import java.util.Date;

@Data
public class Consumption {
    private int userId;
    private double balance;
    private String way;
    private Date date;

}
