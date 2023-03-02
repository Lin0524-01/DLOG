package com.moon.dlog.mapper;

import com.moon.dlog.domain.Plan;
import com.moon.dlog.domain.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-23 20:34
 **/
@Repository
public interface PlanMapper {
    int selectDateFormSpringCalling();

    int addPlan(Plan plan);

    List<Plan> selectPlanList(Plan plan);

    Plan selectPlanById(Long id);

    int updatePlan(Plan plan);

    int deletePlanById(Long id);

    String selectRandomOne(long id);

    int selectPlanCountToday(Plan plan);

    List<Plan> selectPlanListToday(Long id);

    int syncPlanToRecord(Record record);
}
