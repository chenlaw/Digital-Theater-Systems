package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.Coupon;

import java.util.List;

public interface CouponServiceForBl {
    /**
     * 根据用户选择优惠券
     * @param userId
     * @return
     */
    List<Coupon> selectCouponByUser(int userId);

    /**
     * 根据id选择优惠券
     * @param id
     * @return
     */
    Coupon selectCouponById(int id);

    /**
     * 删除coupon_user信息
     * @param couponId
     * @param userId
     */
    void deleteCouponUser(int couponId,int userId);

    /**
     * 插入coupon_user信息
     * @param couponId
     * @param userId
     */
    void insertCouponUser(int couponId,int userId);

}
