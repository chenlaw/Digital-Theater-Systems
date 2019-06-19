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

    /**
     * 插入一条退票信息
     * @param withdrawInfoForm
     * @return
     */
    int insertWithdrawInfo(withdrawInfoForm withdrawInfoForm);

    /**
     * 更新一条退票信息
     * @param withdrawInfoForm
     * @return
     */
    int updateWithdrawInfo(withdrawInfoForm withdrawInfoForm);

    /**
     * 删除一条退票信息
     * @param scheduleId
     * @return
     */
    int deleteWithdrawInfo(int scheduleId);

    /**
     * 根据电影场次查询退票信息
     * @param scheduleId
     * @return
     */
    WithdrawInfo selectWithdrawInfoByScheduleId(int scheduleId);

    /**
     * 选择所有退票信息
     * @return
     */
    List<WithdrawInfo> selectAllWithdrawInfo();

//    @Scheduled(cron = "0/1 * * * * ?")
//    void cleanExpiredWithdrawInfo();

}




