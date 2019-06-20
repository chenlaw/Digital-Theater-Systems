package com.example.cinema.data.record;

import com.example.cinema.po.Consumption;
import com.example.cinema.vo.ConsumptionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface  ConsumptionMapper {
    /**
     * 基于userID查询消费记录
     * @param userId
     * @return
     */
    List<Consumption> selectConsumptions(String userId);

    /**
     * 插入消费信息
     * @param vo
     * @return
     */
    int insertCOnsumptionInfor(ConsumptionVO vo);

    /**
     * 获取所有消费
     * @return
     */
    List<Consumption> getAllConsumptions();
}
