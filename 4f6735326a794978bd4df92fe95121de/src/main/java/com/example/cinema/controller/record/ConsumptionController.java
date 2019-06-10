package com.example.cinema.controller.record;

import com.example.cinema.bl.record.ConsumptionService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumption")
public class ConsumptionController {
    @Autowired
    ConsumptionService consumptionService;
    @GetMapping("/{userId}")
    public ResponseVO getConsumption(@PathVariable String userId){
        return consumptionService.getConsumtions(userId);}
     @GetMapping("/{balance}")
    public ResponseVO getCOnsumptionsByBalance(@PathVariable double balance){
        return ResponseVO.buildSuccess(consumptionService.getConsumtionsOrderByBalance(balance));
     }
}
