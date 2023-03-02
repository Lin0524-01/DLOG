package com.moon.dlog.controller;

import com.moon.dlog.domain.Plan;
import com.moon.dlog.domain.R;
import com.moon.dlog.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-23 20:33
 **/
@RestController
@CrossOrigin
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping("/timeFormSpringCalling")
    public R getTimeFormSpringCalling(){
        int date = planService.getTimeFormSpringCalling();
        return R.ok().data("date", date);
    }

    @PostMapping("/addPlan")
    public R addPlan(@RequestBody Plan plan){
        int result = planService.savePlan(plan);
        if(result == 1){
            return R.ok();
        }
        return R.error().message("添加失败，请稍后再试");
    }

    @PostMapping("/list")
    public R getPlanList(@RequestBody Plan plan){
        List<Plan> planList = planService.getPlanList(plan);
        return R.ok().data("planList", planList);
    }

    @GetMapping("/finishPlan/{id}")
    public R finishPlan(@PathVariable("id") Long id){
        int result = planService.changePlanStatusOfFinished(id);
        if (result == 1) {
            return R.ok();
        }else if (result == 2){
            return R.error().message("计划已过期，不可完成！");
        }
        return R.error().message("已完成，不可再修改！");
    }

    @GetMapping("/findPlanById/{id}")
    public R findPlanById(@PathVariable("id") Long id){
        Plan plan = planService.getPlanById(id);
        return R.ok().data("plan", plan);
    }

    @GetMapping("/deletePlanById/{id}")
    public R deletePlanById(@PathVariable("id") Long id){
        int result = planService.removePlanById(id);
        if (result == 1){
            return R.ok();
        }
        return R.error().message("删除失败！请重试");
    }

    @PostMapping("/updatePlanById/{id}")
    public R updatePlanById(@PathVariable("id") Long id, @RequestBody Plan plan){
        return R.ok();
    }

    @GetMapping("/randomSelectOne")
    public R randomSelectOne(){
        String text = planService.randomSelectOne();
        return R.ok().data("text", text);
    }
}
