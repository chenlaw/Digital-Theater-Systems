package com.example.cinema.bl.record;

import com.example.cinema.vo.RechargeVO;
import com.example.cinema.vo.ResponseVO;

public interface RechargeService {
    public ResponseVO getRechargesByID(String userId);
    public ResponseVO recordRecharge(RechargeVO vo);
}
