package com.example.cinema.blImpl.record;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.record.ConsumptionService;
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
public class ConsumptionImplTest {
    @Autowired
    ConsumptionService consumptionService;
    @Test
    public void recordConsumption(){
        Assert.assertEquals(true,consumptionService.recordConsumption(com.example.cinema.blImpl.record.ConsumptionStub.insertConsumption()).getSuccess());
    }
    @Test
    public void getConsumptionByBalanceTest(){
        Assert.assertEquals(true,consumptionService.getConsumtionsOrderByBalance(34).getSuccess());
    }
    @Test
    public void getConsumptionByIdTest(){
        Assert.assertEquals(true,consumptionService.getConsumtions("3").getSuccess());
    }
}
