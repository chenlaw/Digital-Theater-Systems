package com.example.cinema.blImpl.record;

import com.example.cinema.bl.record.ConsumptionService;
import com.example.cinema.data.record.ConsumptionMapper;
import com.example.cinema.vo.ConsumptionVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {
    @Autowired
    ConsumptionMapper consumtionMapper;

    @Override
    //todo:找到使用到的地方
    public ResponseVO recordRecharge(ConsumptionVO vo) {
        return ResponseVO.buildSuccess(consumtionMapper.insertCOnsumptionInfor(vo));
    }

    @Override
    public ResponseVO getConsumtions(String userId) {
        return ResponseVO.buildSuccess(consumtionMapper.selectConsumptions());
    }



    @Override
    public ResponseVO getConsumtionsOrderByBalance(double balanse) {
        return ResponseVO.buildSuccess(consumtionMapper.selectConsumptionsByOrder(balanse));
    }


}
