package com.moon.dlog.mapper;

import com.moon.dlog.domain.Plan;
import com.moon.dlog.domain.Week;
import com.moon.dlog.domain.WeekPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-19 14:52
 **/
@Repository
public interface WeekMapper {

    void addWeek(Week week);

    Long selectNewWeekId();

    int addWeekPlan(WeekPlan weekPlan);

    List<Week> selectWeekList();

    List<WeekPlan> selectWeekPlanListById(Long id);

    List<WeekPlan> selectWeekPlanList(WeekPlan weekPlan);

    int deleteWeekPlanById(Long id);

    int updateProgress(Plan plan);
}
