package com.moon.dlog.service;

import com.moon.dlog.domain.Week;
import com.moon.dlog.domain.WeekPlan;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-19 14:53
 **/
public interface WeekService {
    void addWeek();

    int addWeekPlan(WeekPlan weekPlan);

    List<Week> getWeekList();

    List<WeekPlan> getWeekPlanListById(Long id);

    List<WeekPlan> getWeekPlanList();

    int removeWeekPlanById(Long id);
}
