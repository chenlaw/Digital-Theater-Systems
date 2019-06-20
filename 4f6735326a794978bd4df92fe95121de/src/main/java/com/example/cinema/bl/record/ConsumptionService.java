package com.example.cinema.bl.record;

import com.example.cinema.vo.ConsumptionVO;
import com.example.cinema.vo.ResponseVO;

public interface ConsumptionService {
    /**
     * 记录一笔交易
     * @param vo
     * @return
     */
    ResponseVO recordConsumption(ConsumptionVO vo);

    /**
     * 获取某人的所有消费记录
     * @param userId
     * @return
     */
    ResponseVO getConsumtions(String userId);

    /**
     * 获得某人的消费总金，基于最低价
     * @param balance
     * @return
     */
    ResponseVO getConsumtionsOrderByBalance(double balance);

    /**
     * 获得所有人的消费总额
     * @return
     */
    ResponseVO getConsumtions();
}
