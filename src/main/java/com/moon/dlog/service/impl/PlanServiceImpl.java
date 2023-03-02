package com.moon.dlog.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.moon.dlog.domain.Plan;
import com.moon.dlog.domain.Record;
import com.moon.dlog.mapper.PlanMapper;
import com.moon.dlog.mapper.WeekMapper;
import com.moon.dlog.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-23 20:34
 **/
@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private WeekMapper weekMapper;

    @Override
    public int getTimeFormSpringCalling() {
        return planMapper.selectDateFormSpringCalling();
    }

    @Override
    public int savePlan(Plan plan) {
        List<Date> dateList = plan.getRangeOfPlanTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String resultDateStr = "";
        for (Date date : dateList) {
            String formatDate = simpleDateFormat.format(date).substring(11, 19);
            resultDateStr = resultDateStr.concat(formatDate + '-');
        }
        plan.setPlanTime(resultDateStr.substring(0, resultDateStr.length() - 1));
        plan.setCreateTime(new Date());
        int result = planMapper.addPlan(plan);

        return result;
    }

    @Override
    public List<Plan> getPlanList(Plan plan) {
        return planMapper.selectPlanList(plan);
    }

    @Override
    public int changePlanStatusOfFinished(Long id) {
        Plan plan = planMapper.selectPlanById(id);
        if ("0".equals(plan.getPlanStatus())){
            // 判断当前时间是否超过截止时间
            if (DateUtil.isExpired(DateUtil.beginOfDay(plan.getCreateTime()), DateField.HOUR_OF_DAY, 24, new Date())){
                plan.setPlanStatus("1");
                plan.setFinishTime(new Date());
                planMapper.updatePlan(plan);
//                weekMapper.updateProgress(plan);
                return 1;
            }
            return 2;
        }
        return 0;
    }

    @Override
    public Plan getPlanById(Long id) {
        return planMapper.selectPlanById(id);
    }

    @Override
    public int removePlanById(Long id) {
        return planMapper.deletePlanById(id);
    }

    @Override
    public String randomSelectOne() {
        long id = (long) ((Math.random() * 9) + 1);
        return planMapper.selectRandomOne(id);
    }

    @Override
    public List<Plan> getPlanListToday(Long id) {
        return planMapper.selectPlanListToday(id);
    }

    @Override
    public int syncPlanToRecord(Record record) {
        record.setStartTime(DateUtil.formatDate(record.getRecordDate()) + " 00:00:00");
        record.setEndTime(DateUtil.formatDate(record.getRecordDate()) + " 23:59:59");
        return planMapper.syncPlanToRecord(record);
    }
}
