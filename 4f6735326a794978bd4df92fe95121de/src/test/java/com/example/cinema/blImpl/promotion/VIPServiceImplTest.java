package com.example.cinema.blImpl.promotion;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.po.VIPInfo;
import com.example.cinema.vo.VIPInfoVO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class VIPServiceImplTest {

    @Autowired
private VIPService vipService;

    @Test
    public void addVIPInfo() {
        VIPInfoVO test = VIPStub.postAddVIPInfo();
        Assert.assertTrue(vipService.addVIPInfo(test).getSuccess());
    }

    @Test
    public void updateVIPInfo() {
        vipService.addVIPInfo(VIPStub.postAddVIPInfo());
        VIPInfoVO test = VIPStub.postUpdateVIPInfo();
        Assert.assertTrue(vipService.updateVIPInfo(test).getSuccess());
    }

    @Test
    public void getAllVIPInfo() {
        Assert.assertTrue(vipService.getAllVIPInfo().getSuccess());
    }



    @Test
    public void getVIPInfoByName() {
        vipService.addVIPInfo(VIPStub.postAddVIPInfo());
        Assert.assertTrue(vipService.getVIPInfoByName("测试卡").getSuccess());
    }

    @Test
    public void getVIPInfoById() {
        Assert.assertTrue(vipService.getVIPInfoById(1).getSuccess());
    }
}