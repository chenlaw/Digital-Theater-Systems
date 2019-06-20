package com.example.cinema.bl.management;

import com.example.cinema.vo.HallForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
public interface HallService {
    /**
     * 搜索所有影厅
     * @return
     */
    ResponseVO searchAllHall();

    /**
     * 添加影厅
     * @param addHallForm
     * @return
     */
    ResponseVO addHall(HallForm addHallForm);

    /**
     * 修改影厅
     * @param updateHallForm
     * @return
     */
    ResponseVO updateHall(HallForm updateHallForm);

    /**
     * 删除影厅
     * @param hallId
     * @return
     */
    ResponseVO deleteHall(Integer hallId);
}
