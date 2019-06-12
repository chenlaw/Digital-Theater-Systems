package com.example.cinema.controller.record;

import com.example.cinema.bl.record.RechargeService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recharge")
public class RechargeController {
    @Autowired
    RechargeService rechargeService;
    @GetMapping("/get/{userId}")
    public ResponseVO getRechargeById(@PathVariable String userId){
        return rechargeService.getRechargesByID(userId);
    }
}
