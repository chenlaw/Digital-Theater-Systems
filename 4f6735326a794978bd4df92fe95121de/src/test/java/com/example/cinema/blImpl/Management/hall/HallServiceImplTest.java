package com.example.cinema.blImpl.Management.hall;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.management.HallService;
import com.example.cinema.vo.HallForm;
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
public class HallServiceImplTest {
@Autowired
private HallService hallService;

    @Test
    public void addHall() {
        HallForm hallForm=new HallForm();
        hallForm.setName("7号厅");
        hallForm.setRow(10);
        hallForm.setColumn(17);
        Assert.assertTrue(hallService.addHall(hallForm).getSuccess());
    }

    @Test
    public void updateHall() {
        HallForm hallForm=new HallForm();
        hallForm.setId(7);
        hallForm.setName("7号厅");
        hallForm.setRow(10);
        hallForm.setColumn(17);
        Assert.assertTrue(hallService.updateHall(hallForm).getSuccess());
    }

    @Test
    public void deleteHall() {
        Integer num = new Integer(2);
        Assert.assertTrue(hallService.deleteHall(num).getSuccess());
    }
}