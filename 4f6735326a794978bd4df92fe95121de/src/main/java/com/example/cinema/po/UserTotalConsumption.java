package com.example.cinema.po;


public class UserTotalConsumption {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户总消费额
     */
    private double balance;
    /**
     * 用户id
     */
    private int userId;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
