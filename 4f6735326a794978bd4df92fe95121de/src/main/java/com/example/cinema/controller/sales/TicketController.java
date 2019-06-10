package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;
import com.example.cinema.vo.TicketInfoVO;
import com.example.cinema.vo.withdrawInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/vip/buy")
    public ResponseVO buyTicketByVIPCard(@RequestBody TicketInfoVO ticketInfoVO){
        return ticketService.completeByVIPCard(ticketInfoVO);
    }

    @PostMapping("/lockSeat")
    public ResponseVO lockSeat(@RequestBody TicketForm ticketForm){
        return ticketService.addTicket(ticketForm);
    }
    @PostMapping("/buy")
    public ResponseVO buyTicket(@RequestBody TicketInfoVO ticketInfoVO){
        return ticketService.completeTicket(ticketInfoVO);
    }
    @GetMapping("/get/{userId}")
    public ResponseVO getTicketByUserId(@PathVariable int userId){
        return ticketService.getTicketByUser(userId);
    }

    @GetMapping("/get/occupiedSeats")
    public ResponseVO getOccupiedSeats(@RequestParam int scheduleId){
        return ticketService.getBySchedule(scheduleId);
    }
    @PostMapping("/cancel")
    public ResponseVO cancelTicket(@RequestParam List<Integer> ticketId){
        return ticketService.cancelTicket(ticketId);
    }
    @GetMapping("/withdraw")
    public ResponseVO withdrawTicket(@RequestParam int ticketId){
        return ticketService.withdrawTicket(ticketId);
    }

    @GetMapping("/withdraw/getAll")
    public ResponseVO getAllWithdrawInfo(){ return ticketService.getAllWithdrawInfo();}

    @PostMapping("/withdraw/add")
    public ResponseVO addWithdrawInfo(@RequestBody withdrawInfoForm withdrawInfoForm){ return ticketService.addWithdrawInfo(withdrawInfoForm); }

    @PostMapping("/withdraw/update")
    public ResponseVO updateWithdrawInfo(@RequestBody withdrawInfoForm withdrawInfoForm){return ticketService.updateWithdrawInfo(withdrawInfoForm);}

    @GetMapping("/withdraw/delete")
    public ResponseVO deleteWithdrawInfo(@RequestParam int scheduleId){ return ticketService.deleteWithdrawInfo(scheduleId); }




}
