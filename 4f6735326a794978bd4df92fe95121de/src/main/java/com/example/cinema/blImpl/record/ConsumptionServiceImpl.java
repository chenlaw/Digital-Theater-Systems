package com.example.cinema.blImpl.record;

import com.example.cinema.bl.record.ConsumptionService;
import com.example.cinema.data.record.ConsumptionMapper;
import com.example.cinema.data.user.AccountMapper;
import com.example.cinema.po.Consumption;
import com.example.cinema.po.UserTotalConsumption;
import com.example.cinema.vo.ConsumptionVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumptionServiceImpl implements ConsumptionService,ConsumptionServiceForBl{
    @Autowired
    ConsumptionMapper consumtionMapper;
    @Autowired
    AccountMapper accountMapper;

    @Override
    public void recordConsumptionForBl(ConsumptionVO vo) {
        consumtionMapper.insertCOnsumptionInfor(vo);
    }
    @Override
    public ResponseVO recordConsumption(ConsumptionVO vo) {
        return ResponseVO.buildSuccess(consumtionMapper.insertCOnsumptionInfor(vo));
    }

    @Override
    public ResponseVO getConsumtions(String userId) {
        return ResponseVO.buildSuccess(consumtionMapper.selectConsumptions(userId));
    }



    @Override
    public ResponseVO getConsumtionsOrderByBalance(double balanse) {
        return ResponseVO.buildSuccess(toTotoalConsumptions(
                consumtionMapper.getAllConsumptions()).stream().filter(e-> e.getBalance()>balanse).toArray());
    }

    @Override
    public ResponseVO getConsumtions() {
        return ResponseVO.buildSuccess(
               toTotoalConsumptions( consumtionMapper.getAllConsumptions()));
    }
    public ArrayList<UserTotalConsumption> toTotoalConsumptions(List<Consumption> consumptions){
        ArrayList<UserTotalConsumption> userTotalConsumptions=new ArrayList<>();
        for (Consumption consumption:consumptions){
            UserTotalConsumption consumption1=new UserTotalConsumption();
            consumption1.setBalance(consumption.getBalance());
            consumption1.setUserId(consumption.getUserId());
            consumption1.setUsername(accountMapper.getAccountById(consumption.getUserId()).getUsername());
            userTotalConsumptions.add(consumption1);
        }
        return userTotalConsumptions;
    }

}
