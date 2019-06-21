package com.example.cinema.blImpl.sales;

import com.example.cinema.vo.SeatForm;
import com.example.cinema.vo.TicketForm;
import com.example.cinema.vo.withdrawInfoForm;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.withdrawInfoForm;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import java.util.ArrayList;
import java.util.Date;

public class WithdrawInfoStub {
    @Autowired
private static TicketService ticketService;

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
