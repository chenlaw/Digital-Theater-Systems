package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.Coupon;

import java.util.List;

public interface CouponServiceForBl {

    List<Coupon> selectCouponByUser(int userId);

    Coupon selectCouponById(int id);

    void deleteCouponUser(int couponId,int userId);

    void insertCouponUser(int couponId,int userId);

}
