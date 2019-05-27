package com.example.cinema.data.sales;


import com.example.cinema.po.WithdrawInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by liying on 2019/4/16.
 */
@Mapper
public interface WithdrawMapper {

    int insertWithdrawInfo(WithdrawInfo withdrawInfo);

    WithdrawInfo selectWithdrawInfoByScheduleId(int scheduleId);

}

