package com.example.cinema.bl.promotion;

import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.VIPInfoVO;
import com.example.cinema.vo.ResponseVO;



/**
 * Created by liying on 2019/4/14.
 */

public interface VIPService {

    ResponseVO addVIPCard(int userId,int vipInfoId);

    ResponseVO getCardById(int id);

    /**
     * 购买会员卡前，获取会员卡各个种类信息
     * @author wph
     * @param name
     * @return ResponseVO
     */
    ResponseVO getVIPInfoByName(String name);

    /**
     * 购买会员卡前，获取会员卡各个种类信息
     * @author wph
     * @param ID
     * @return ResponseVO
     */
    ResponseVO getVIPInfoById(int ID);

    ResponseVO charge(VIPCardForm vipCardForm);

    ResponseVO getCardByUserId(int userId);
    /**
     * 增加会员卡种类
     * @author wph
     * @param vipInfoVO
     * @return
     */
	ResponseVO addVIPInfo(VIPInfoVO vipInfoVO);

    /**
     * 取得所有会员卡种类信息
     * @author wph
     * @return ResponseVO
     */
	ResponseVO getAllVIPInfo();

    /**
     * 修改会员卡种类信息
     * @author wph
     * @param vipInfoVO
     * @return ResponseVO
     */
    ResponseVO updateVIPInfo(VIPInfoVO vipInfoVO);
}