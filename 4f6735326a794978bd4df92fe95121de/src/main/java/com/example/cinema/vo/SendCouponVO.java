package com.example.cinema.vo;

public class SendCouponVO {
    private int[] usersId;
    private int[] couponsId;

    public int[] getUsersId() {
        return usersId;
    }

    public void setUsersId(int[] usersId) {
        this.usersId = usersId;
    }

    public int[] getCouponsId() {
        return couponsId;
    }

    public void setCouponsId(int[] couponsId) {
        this.couponsId = couponsId;
    }
}
