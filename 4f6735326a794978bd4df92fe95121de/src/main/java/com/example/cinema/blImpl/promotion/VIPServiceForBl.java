package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.VIPCard;

public interface VIPServiceForBl {

    /**
     * 根据userId选择vipcard
     * @param userId
     * @return
     */
    VIPCard selectCardByUserId(int userId);

    /**
     * 更新会员卡余额
     * @param id
     * @param balance
     */
    void updateCardBalance(int id,double balance);

}
