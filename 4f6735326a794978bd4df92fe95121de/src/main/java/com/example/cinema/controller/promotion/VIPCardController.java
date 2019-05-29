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

    @PostMapping("/add/{userId}/{vipInfoId}")
    public ResponseVO addVIP(@PathVariable int userId,@PathVariable int vipInfoId){
        return vipService.addVIPCard(userId,vipInfoId);
    }
    @GetMapping("{userId}/get")
    public ResponseVO getVIP(@PathVariable int userId){
        return vipService.getCardByUserId(userId);
    }

    @GetMapping("/getVIPInfo/{name}")
    public ResponseVO getVIPInfo(@PathVariable String name){
        return vipService.getVIPInfoByName(name);
    }

    @GetMapping("/getVIPInfoById/{vipInfoId}")
    public ResponseVO getVIPInfoById(@PathVariable int vipInfoId){
        return vipService.getVIPInfoById(vipInfoId);
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

    @PostMapping("/update")
    public ResponseVO updateVIPInfo(@RequestBody VIPInfoVO vipInfoVO){
        return  vipService.updateVIPInfo(vipInfoVO);
    }



}
