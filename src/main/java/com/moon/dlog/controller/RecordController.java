package com.moon.dlog.controller;

import com.moon.dlog.domain.Plan;
import com.moon.dlog.domain.R;
import com.moon.dlog.domain.Record;
import com.moon.dlog.domain.vo.RecordAccountVo;
import com.moon.dlog.service.AccountService;
import com.moon.dlog.service.PlanService;
import com.moon.dlog.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-08 14:35
 **/
@RestController
@CrossOrigin
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @Autowired
    private PlanService planService;

    @Autowired
    private AccountService accountService;

    /**
     * 新增Record记录
     * @param record
     * @return
     */
    @PostMapping("/addRecord")
    public R addRecord(@RequestBody Record record){
        try {
            recordService.saveRecord(record);
            return R.ok();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return R.error().message("添加失败！");
    }

    /**
     * 条件查询
     * @param record
     * @return
     */
    @PostMapping("/list/{pageNum}/{pageSize}")
    public R getRecordList(@RequestBody Record record, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        List<Record> recordList = recordService.getRecordList(record, pageNum, pageSize);
        int total = recordService.getTotalCount();

        return R.ok().data("recordList", recordList).data("total", total);
    }

    /**
     * 通过id查询record，element-ui抽屉显示今日计划列表
     * @param id
     * @return
     */
    @GetMapping("/getRecordById/{id}")
    public R getRecordById(@PathVariable Long id){
        Record record = recordService.getRecordById(id);
        List<Plan> planList = planService.getPlanListToday(id);
        return R.ok().data("record", record).data("planList", planList);
    }

    /**
     * 得到今日收入和支出  element-ui抽屉描述列表显示
     * @return
     */
    @PostMapping("/getIncomeAndExpenseToday")
    public R getIncomeAndExpenseToday(@RequestBody Record record){
        RecordAccountVo recordAccountVo = accountService.getIncomeAndExpenseToday(record);
        return R.ok().data("recordAccountVo", recordAccountVo);
    }

    @PostMapping("/updateScore")
    public R updateScore(@RequestBody Record record){
        recordService.updateScore(record, accountService.getIncomeAndExpenseToday(record));
        return R.ok();
    }

    @GetMapping("removeRecordById/{id}")
    public R removeRecordById(@PathVariable Long id){
        int result = recordService.removeRecordById(id);
        return R.ok();
    }

    @GetMapping("/getReachStandardCurrentYearDays")
    public R getReachStandardCurrentYearDays(){
        int days = recordService.getReachStandardCurrentYearDays();
        int endDays = recordService.getEndDaysCurrentYear();
        return R.ok().data("totalDays", days).data("endDays", endDays);
    }

    @PostMapping("/syncPlan")
    public R syncPlan(@RequestBody Record record){
        planService.syncPlanToRecord(record);
        int result = recordService.syncPlanStatus(record);
        return R.ok();
    }

    /**
     * 本月任务完成情况 echarts折线图接口
     * @return
     */
    @GetMapping("/getCurrentMonthPlanAchieveStatus")
    public R getCurrentMonthPlanAchieveStatus(){
        List<String> dateList = recordService.getCurrentMonthDateList();

        List<Integer> planSummaryList = recordService.getCurrentMonthPlanSummaryList();
        List<Integer> planAchieveList = recordService.getCurrentMonthPlanAchieveList();
        return R.ok().data("dateList", dateList).data("planSummaryList", planSummaryList).data("planAchieveList", planAchieveList);
    }
}
