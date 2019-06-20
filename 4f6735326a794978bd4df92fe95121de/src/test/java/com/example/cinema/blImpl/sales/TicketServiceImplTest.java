package test.com.example.cinema.blImpl.sales;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.sales.TicketService;
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
    public void testAddTicket() throws Exception {
//TODO: Test goes here...
        TicketForm ticketForm=new TicketForm();
        ticketForm.setUserId(13);
        ticketForm.setScheduleId(71);
        List<SeatForm> list=new ArrayList<>();
        list.add(new SeatForm(1,2));
        ticketForm.setSeats(list);
        Assert.assertTrue(ticketService.addTicket(ticketForm).getSuccess());
    }

    @Test
    public void testCompleteTicket() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testGetBySchedule() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testGetTicketByUser() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testCompleteByVIPCard() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testCancelTicket() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testWithdrawTicket() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testGetAllWithdrawInfo() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testAddWithdrawInfo() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testUpdateWithdrawInfo() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testDeleteWithdrawInfo() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testSelectTicketsBySchedule() throws Exception {
//TODO: Test goes here...
    }


    @Test
    public void testTicketList2TicketWithScheduleVOList() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testWithdrawInfo2withdrawVO() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testPrecheck() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testPrecheck1() throws Exception {
//TODO: Test goes here...
    }

}
