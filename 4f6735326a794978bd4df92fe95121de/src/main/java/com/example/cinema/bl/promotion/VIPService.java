package com.example.cinema.bl.promotion;

import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.VIPInfoVO;
import com.example.cinema.vo.ResponseVO;



/**
 * Created by liying on 2019/4/14.
 */

public interface VIPService {

    ResponseVO addVIPCard(int userId);

    ResponseVO getCardById(int id);

    ResponseVO getVIPInfoByName(String name);//购买会员卡前 获取会员卡种类
    
    ResponseVO getVIPInfoByUserId(int userID);//购买会员卡后 用户查看自己卡的信息

    ResponseVO charge(VIPCardForm vipCardForm);

    ResponseVO getCardByUserId(int userId);

	ResponseVO addVIPInfo(VIPInfoVO vipInfoVO);//增加会员卡种类

	ResponseVO getAllVIPInfo();//取得所有会员卡

    ResponseVO updateYIPInfo(VIPInfoVO vipInfoVO);//修改卡信息
}
