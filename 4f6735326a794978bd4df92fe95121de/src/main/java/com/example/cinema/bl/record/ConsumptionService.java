package com.example.cinema.bl.record;

import com.example.cinema.vo.ConsumptionVO;
import com.example.cinema.vo.ResponseVO;

public interface ConsumptionService {
    ResponseVO recordRecharge(ConsumptionVO vo);
    ResponseVO getConsumtions(String userId);
    ResponseVO getConsumtionsOrderByBalance(double balance);
}
