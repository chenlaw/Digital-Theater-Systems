package com.example.cinema.vo;


import java.util.List;

public class TicketInfoVO {

    /**
     * 电影票id
     */
    private List<Integer> ticketId;

    /**
     * 优惠券id
     */
    private  int couponId;

    public List<Integer> getTicketId() {
        return ticketId;
    }

    public void setTicketId(List<Integer> ticketId) {
        this.ticketId = ticketId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }
}
