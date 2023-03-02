package com.moon.dlog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.moon.dlog.domain.Week;
import com.moon.dlog.domain.WeekPlan;
import com.moon.dlog.mapper.WeekMapper;
import com.moon.dlog.service.WeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-19 14:53
 **/
@Service
public class WeekServiceImpl implements WeekService {
    @Autowired
    private WeekMapper weekMapper;

    @Override
    public void addWeek() {
        Week week = new Week();
        Date date = new Date();
        week.setWeek(DateUtil.weekOfYear(date));
        week.setStartDate(DateUtil.beginOfWeek(date));
        week.setEndDate(DateUtil.endOfWeek(date));
        weekMapper.addWeek(week);
    }

    @Override
    public int addWeekPlan(WeekPlan weekPlan) {
        weekPlan.setCreateTime(new Date());
        weekPlan.setWeekId(weekMapper.selectNewWeekId());
        return weekMapper.addWeekPlan(weekPlan);
    }

    @Override
    public List<Week> getWeekList() {
        return weekMapper.selectWeekList();
    }

    @Override
    public List<WeekPlan> getWeekPlanListById(Long id) {
        return weekMapper.selectWeekPlanListById(id);
    }

    @Override
    public List<WeekPlan> getWeekPlanList(){
        WeekPlan weekPlan = new WeekPlan();
        weekPlan.setStartDate(DateUtil.formatDate(DateUtil.beginOfWeek(new Date())));
        weekPlan.setEndDate(DateUtil.formatDate(DateUtil.endOfWeek(new Date())));
        return weekMapper.selectWeekPlanList(weekPlan);
    }

    @Override
    public int removeWeekPlanById(Long id) {
        return weekMapper.deleteWeekPlanById(id);
    }
}
