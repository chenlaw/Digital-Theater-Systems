package com.example.cinema.blImpl.record;

import com.example.cinema.CinemaApplication;
import com.example.cinema.blImpl.record.RechargeServiceImpl;
import com.example.cinema.vo.ResponseVO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class RechargeServiceImplTest{
    @Autowired
    RechargeServiceImpl rechargeService;
    @Test
    public void recordRechargeTest(){
        Assert.assertEquals(true,rechargeService.recordRecharge(RechargeStub.insertRecharge()).getSuccess());
    }
    @Test
    public void getRecharge(){
        Assert.assertEquals(true,rechargeService.getRechargesByID("15").getSuccess());
    }
}