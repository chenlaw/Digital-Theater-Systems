package com.example.cinema.blImpl.promotion;

import com.example.cinema.vo.UserVO;
import com.example.cinema.vo.VIPInfoVO;

public class VIPStub {
    public static VIPInfoVO postAddVIPInfo(){
        VIPInfoVO test = new VIPInfoVO();
        test.setDescription("测试");
        test.setName("测试卡");
        test.setPrice(100);
        test.setMinimumCharge(23);
        test.setExtraCharge(1);
        return test;
    }

    public static VIPInfoVO postUpdateVIPInfo(){
        VIPInfoVO test = new VIPInfoVO();
        test.setDescription("测试");
        test.setName("测试卡");
        test.setPrice(1100);
        test.setMinimumCharge(232);
        test.setExtraCharge(10);
        return test;
    }
}
