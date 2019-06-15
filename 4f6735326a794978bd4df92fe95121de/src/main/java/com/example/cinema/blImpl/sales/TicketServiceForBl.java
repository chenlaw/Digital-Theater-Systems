package com.example.cinema.blImpl.sales;

import com.example.cinema.po.Ticket;

import java.util.List;

public interface TicketServiceForBl {

    List<Ticket> selectTicketsBySchedule(int id);

}
