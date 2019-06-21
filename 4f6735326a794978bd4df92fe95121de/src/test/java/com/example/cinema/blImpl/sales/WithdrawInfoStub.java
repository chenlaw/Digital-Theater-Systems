package com.example.cinema.blImpl.sales;

import com.example.cinema.vo.withdrawInfoForm;

import java.util.Date;

public class WithdrawInfoStub {

    public static withdrawInfoForm postAddWithdrawInfo(){
        withdrawInfoForm test = new withdrawInfoForm();
        test.setWithdrawDescription("test");
        test.setDiscount(0.9);
        test.setScheduleId(70);
        test.setCloseTime(new Date());
        return test;
    }

    public static withdrawInfoForm postUpdateWithdrawInfo(){
        withdrawInfoForm test = new withdrawInfoForm();
        test.setWithdrawDescription("test");
        test.setDiscount(0.9);
        test.setScheduleId(0);
        test.setCloseTime(new Date());
        return test;
    }

}
