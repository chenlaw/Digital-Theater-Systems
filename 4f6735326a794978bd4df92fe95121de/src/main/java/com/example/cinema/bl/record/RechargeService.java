package com.example.cinema.bl.record;

import com.example.cinema.vo.RechargeVO;
import com.example.cinema.vo.ResponseVO;

public interface RechargeService {
    /**
     * 通过userId获取充值记录
     * @param userId
     * @return
     */
    public ResponseVO getRechargesByID(String userId);

    /**
     * 记录一笔充值
     * @param vo
     * @return
     */
    public ResponseVO recordRecharge(RechargeVO vo);
}
