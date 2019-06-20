package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.Coupon;

import java.util.List;


/**
 * @author ttc
 */
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
     * 赠送优惠卷给用户
     * @param usersId
     * @param couponsId
     */
    void sendCoupons(int[] usersId, int[] couponsId);

    /**
     * 插入coupon_user信息
     * @param couponId
     * @param userId
     */
    void insertCouponUser(int couponId,int userId);

}
