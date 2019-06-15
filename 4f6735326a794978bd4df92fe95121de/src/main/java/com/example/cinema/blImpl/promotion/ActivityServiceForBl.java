package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.Activity;

import java.util.List;

public interface ActivityServiceForBl {

    List<Activity> selectActivities();

    List<Activity> selectActivitiesByMovie(int movieId);

}
