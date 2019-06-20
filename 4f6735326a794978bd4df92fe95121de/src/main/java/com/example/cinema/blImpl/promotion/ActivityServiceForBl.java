package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.Activity;

import java.util.List;

/**
 * @author ttc
 */
public interface ActivityServiceForBl {

    /**
     * 选择所有活动
     * @return
     */
    List<Activity> selectActivities();

    /**
     * 根据电影id选择活动
     * @param movieId
     * @return
     */
    List<Activity> selectActivitiesByMovie(int movieId);

}
