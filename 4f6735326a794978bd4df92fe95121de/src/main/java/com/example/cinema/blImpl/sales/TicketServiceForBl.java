package com.example.cinema.blImpl.sales;

import com.example.cinema.po.Ticket;

import java.util.List;

public interface TicketServiceForBl {

    /**
     * 根据电影场次选择电影票
     * @param id
     * @return
     */
    List<Ticket> selectTicketsBySchedule(int id);

}
