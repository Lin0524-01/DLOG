package com.moon.dlog.controller;

import com.moon.dlog.domain.R;
import com.moon.dlog.domain.Week;
import com.moon.dlog.domain.WeekPlan;
import com.moon.dlog.service.WeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-19 14:51
 **/
@RestController
@CrossOrigin
@RequestMapping("/week")
public class WeekController {
    @Autowired
    private WeekService weekService;

    @GetMapping("/addWeek")
    public R addWeek(){
        weekService.addWeek();
        return R.ok();
    }

    @PostMapping("/addWeekPlan")
    public R addWeekPlan(@RequestBody WeekPlan weekPlan){
        int result = weekService.addWeekPlan(weekPlan);
        return R.ok();
    }

    @GetMapping("/list")
    public R list(){
        List<Week> weekList = weekService.getWeekList();
        return R.ok().data("weekList", weekList);
    }

    @GetMapping("/getWeekPlanList/{id}")
    public R getWeekPlanListById(@PathVariable Long id){
        List<WeekPlan> weekPlanList = weekService.getWeekPlanListById(id);
        return R.ok().data("weekPlanList", weekPlanList);
    }

    @GetMapping("/getWeekPlanList")
    public R getWeekPlanList(){
        List<WeekPlan> weekPlanList = weekService.getWeekPlanList();
        return R.ok().data("weekPlanList", weekPlanList);
    }

    @GetMapping("/removeWeekPlanById/{id}")
    public R removeWeekPlanById(@PathVariable Long id){
        weekService.removeWeekPlanById(id);
        return R.ok();
    }
}
