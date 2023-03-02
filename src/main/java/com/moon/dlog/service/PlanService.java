package com.moon.dlog.service;

import com.moon.dlog.domain.Plan;
import com.moon.dlog.domain.Record;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-23 20:34
 **/
public interface PlanService {
    int getTimeFormSpringCalling();

    int savePlan(Plan plan);

    List<Plan> getPlanList(Plan plan);

    int changePlanStatusOfFinished(Long id);

    Plan getPlanById(Long id);

    int removePlanById(Long id);

    String randomSelectOne();

    List<Plan> getPlanListToday(Long id);

    int syncPlanToRecord(Record record);
}
