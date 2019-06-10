package com.example.cinema.data.record;

import com.example.cinema.po.Consumption;
import com.example.cinema.vo.ConsumptionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface  ConsumptionMapper {
    List<Consumption> selectConsumptions();
    int insertCOnsumptionInfor(ConsumptionVO vo);
    List<Consumption> selectConsumptionsByOrder(double balance);
}
