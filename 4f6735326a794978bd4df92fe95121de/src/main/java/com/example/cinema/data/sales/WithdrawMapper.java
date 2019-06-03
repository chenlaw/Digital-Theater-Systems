package com.example.cinema.data.sales;


import com.example.cinema.po.WithdrawInfo;
import com.example.cinema.vo.withdrawInfoForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Mapper
public interface WithdrawMapper {

    int insertWithdrawInfo(withdrawInfoForm withdrawInfoForm);

    int updateWithdrawInfo(withdrawInfoForm withdrawInfoForm);

    WithdrawInfo selectWithdrawInfoByScheduleId(int scheduleId);

    List<WithdrawInfo> selectAllWithdrawInfo();

}




