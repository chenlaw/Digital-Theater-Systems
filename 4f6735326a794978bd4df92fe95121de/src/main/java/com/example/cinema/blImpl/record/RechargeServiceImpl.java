package com.example.cinema.blImpl.record;

import com.example.cinema.bl.record.RechargeService;
import com.example.cinema.data.record.RechargeMapper;
import com.example.cinema.vo.RechargeVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    RechargeMapper rechargeMapper;
    public ResponseVO getRechargesByID(String userId) {
        return ResponseVO.buildSuccess(rechargeMapper.selectRecharges(userId));
    }

    public ResponseVO recordRecharge(RechargeVO vo) {
        return ResponseVO.buildSuccess(rechargeMapper.insertRechargeInfor(vo));
    }
}
