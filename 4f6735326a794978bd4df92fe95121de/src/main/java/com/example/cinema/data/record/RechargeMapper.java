package com.example.cinema.data.record;

import com.example.cinema.po.Recharge;
import com.example.cinema.vo.RechargeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RechargeMapper {
    List<Recharge> selectRecharges(String userId);
    int insertRechargeInfor(RechargeVO vo);
}
