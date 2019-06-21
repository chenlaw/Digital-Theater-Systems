package com.example.cinema.blImpl.sales;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.sales.WithdrawInfoStub;
import com.example.cinema.vo.SeatForm;
import com.example.cinema.vo.TicketForm;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;


/**
 * TicketServiceImpl Tester.
 * @author <Authors name>
 */

@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketServiceImplTest {

    @Autowired
    TicketService ticketService;


    @Test
    public void testWithdrawTicket() throws Exception {

        Assert.assertEquals(true,ticketService.withdrawTicket(1).getSuccess());
    }

    @Test
    public void testGetAllWithdrawInfo() throws Exception {
        Assert.assertTrue(ticketService.getAllWithdrawInfo().getSuccess());
    }

    @Test
    public void testAddWithdrawInfo() throws Exception {
        Assert.assertEquals(false,ticketService.addWithdrawInfo(WithdrawInfoStub.postAddWithdrawInfo()).getSuccess());


    }

    @Test
    public void testUpdateWithdrawInfo() throws Exception {
        Assert.assertEquals(true,ticketService.addWithdrawInfo(WithdrawInfoStub.postUpdateWithdrawInfo()).getSuccess());
    }

    @Test
    public void testDeleteWithdrawInfo() throws Exception {
        Assert.assertEquals(false,ticketService.deleteWithdrawInfo(1).getSuccess());

    }


}
