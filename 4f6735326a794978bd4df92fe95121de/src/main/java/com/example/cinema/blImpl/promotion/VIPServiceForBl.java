package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.VIPCard;

public interface VIPServiceForBl {

    VIPCard selectCardByUserId(int userId);

    void updateCardBalance(int id,double balance);

}
