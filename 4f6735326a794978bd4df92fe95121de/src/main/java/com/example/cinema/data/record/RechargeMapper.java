package com.example.cinema.data.record;

import com.example.cinema.po.Recharge;
import com.example.cinema.vo.RechargeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RechargeMapper {
    /**
     * 基于userid查询消费记录
     * @param userId
     * @return
     */
    List<Recharge> selectRecharges(String userId);

    /**
     * 插入充值信息
     * @param vo
     * @return
     */
    int insertRechargeInfor(RechargeVO vo);
}
