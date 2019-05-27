package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.VIPInfoVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liying on 2019/4/14.
 */
@RestController()
@RequestMapping("/vip")
public class VIPCardController {
    @Autowired
    VIPService vipService;

    @PostMapping("/add")
    public ResponseVO addVIP(@RequestParam int userId){
        return vipService.addVIPCard(userId);
    }
    @GetMapping("{userId}/get")
    public ResponseVO getVIP(@PathVariable int userId){
        return vipService.getCardByUserId(userId);
    }

    @GetMapping("/getVIPInfo/{name}")
    public ResponseVO getVIPInfo(@PathVariable String name){
        return vipService.getVIPInfoByName(name);
    }

    @GetMapping("/getVIPInfo/{userId}")
    public ResponseVO getVIPInfoById(@PathVariable int userId){
        return vipService.getVIPInfoByUserId(userId);
    }
    
    @GetMapping("/getAllVIPInfo")
    public ResponseVO getAllVIPInfo(){
        return vipService.getAllVIPInfo();
    }
    
    
    @PostMapping("/charge")
    public ResponseVO charge(@RequestBody VIPCardForm vipCardForm){
        return vipService.charge(vipCardForm);
    }
    
    @PostMapping("/addVIPInfo")
    public ResponseVO addVIPInfo(@RequestBody VIPInfoVO vipInfoVO){
        return vipService.addVIPInfo(vipInfoVO);
    }



}
