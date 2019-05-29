package com.example.cinema.po;


import java.sql.Timestamp;

/**
 * Created by liying on 2019/4/14.
 */

public class VIPCard {

    public int getVipInfoId() {
        return vipInfoId;
    }

    public void setVipInfoId(int vipInfoId) {
        this.vipInfoId = vipInfoId;
    }

    /**
     * VIP信息
     */
    private int vipInfoId;
    
    /**
     * 用户id
     */
    private int userId;

    /**
     * 会员卡id
     */
    private int id;

    /**
     * 会员卡余额
     */
    private double balance;

    /**
     * 办卡日期
     */
    private Timestamp joinDate;


    public VIPCard() {

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public double calculate(double amount,double minimumCharge,double extraCharge) {
        System.out.println((int)(amount/minimumCharge)*extraCharge);
        System.out.println(amount);
        return (int)(amount/minimumCharge)*extraCharge+amount;

    }
    

    
}
