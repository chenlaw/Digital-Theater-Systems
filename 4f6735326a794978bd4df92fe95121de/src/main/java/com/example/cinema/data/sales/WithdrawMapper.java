package com.example.cinema.data.sales;


import com.example.cinema.po.WithdrawInfo;
import com.example.cinema.vo.withdrawInfoForm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Mapper
public interface WithdrawMapper {

    int insertWithdrawInfo(withdrawInfoForm withdrawInfoForm);

    int updateWithdrawInfo(withdrawInfoForm withdrawInfoForm);

    int deleteWithdrawInfo(int scheduleId);

    WithdrawInfo selectWithdrawInfoByScheduleId(int scheduleId);

    List<WithdrawInfo> selectAllWithdrawInfo();

//    @Scheduled(cron = "0/1 * * * * ?")
//    void cleanExpiredWithdrawInfo();

}




