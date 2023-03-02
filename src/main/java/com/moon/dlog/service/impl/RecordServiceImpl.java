package com.moon.dlog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.moon.dlog.domain.Plan;
import com.moon.dlog.domain.Record;
import com.moon.dlog.domain.vo.RecordAccountVo;
import com.moon.dlog.mapper.AccountMapper;
import com.moon.dlog.mapper.PlanMapper;
import com.moon.dlog.mapper.RecordMapper;
import com.moon.dlog.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-08 14:35
 **/
@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int saveRecord(Record record) {
        Plan plan = new Plan();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 格式化日期设置起始时间
        String formatDate = simpleDateFormat.format(date);
        plan.setStartTime(formatDate + " 00:00:00");
        plan.setEndTime(formatDate + " 23:59:59");

        // 查询今日计划总数
        record.setPlanSummary(planMapper.selectPlanCountToday(plan));
        // 查询今日计划完成数量
        plan.setPlanStatus("1");
        record.setPlanAchieve(planMapper.selectPlanCountToday(plan));
        record.setRecordDate(new Date());
        return recordMapper.addRecord(record);
    }

    @Override
    public List<Record> getRecordList(Record record, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Record> recordList = recordMapper.selectRecordList(record);
        for (Record srcRecord : recordList) {
            if (srcRecord.getPlanSummary() != 0){
                srcRecord.setPlanProcess(100 *( (double)srcRecord.getPlanAchieve() / srcRecord.getPlanSummary()));
            }else {
                srcRecord.setPlanProcess(0.00);
            }
        }
        return recordList;
    }

    @Override
    public Record getRecordById(Long id) {
        return recordMapper.selectRecordById(id);
    }

    @Override
    public int updateScore(Record record, RecordAccountVo recordAccountVo) {
        if (record.getPlanSummary() != 0){
            record.setPlanProcess(100 *( (double)record.getPlanAchieve() / record.getPlanSummary()));
        }else {
            record.setPlanProcess(0.0);
        }
        record.setScore(score(record, recordAccountVo));
        return recordMapper.updateRecord(record);
    }

    @Override
    public int removeRecordById(Long id) {
        return recordMapper.deleteRecordById(id);
    }

    @Override
    public int getReachStandardCurrentYearDays() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String formatDate = simpleDateFormat.format(date);

        return recordMapper.selectReachStandardCurrentYearDays(formatDate + "-12-31");
    }

    @Override
    public int getEndDaysCurrentYear() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String formatDate = simpleDateFormat.format(date);

        return recordMapper.selectEndDays(formatDate + "-12-31");
    }

    @Override
    public List<String> getCurrentMonthDateList() {
        List<String> dateList = new ArrayList<>();
        Record record = new Record();
        record.setStartTime(DateUtil.format(DateUtil.beginOfMonth(new Date()), "yyyy-MM-dd"));
        record.setEndTime(DateUtil.format(DateUtil.endOfMonth(new Date()), "yyyy-MM-dd"));

        List<Record> recordList = recordMapper.selectRecordList(record);
        for (Record rec : recordList) {
            dateList.add(DateUtil.formatDate(rec.getRecordDate()));
        }

        return dateList;
    }

    @Override
    public List<Integer> getCurrentMonthPlanSummaryList() {
        List<Integer> planSummaryList = new ArrayList<>();

        List<Record> recordList = backRecordList();
        for (Record rec : recordList) {
            planSummaryList.add(rec.getPlanSummary());
        }
        return planSummaryList;
    }

    @Override
    public List<Integer> getCurrentMonthPlanAchieveList() {
        List<Integer> planAchieveList = new ArrayList<>();
        List<Record> recordList = backRecordList();
        for (Record rec : recordList) {
            planAchieveList.add(rec.getPlanAchieve());
        }
        return planAchieveList;
    }

    @Override
    public int syncPlanStatus(Record record) {
        Plan plan = new Plan();
        plan.setStartTime(DateUtil.formatDate(record.getRecordDate()) + " 00:00:00");
        plan.setEndTime(DateUtil.formatDate(record.getRecordDate()) + " 23:59:59");
        record.setPlanSummary(planMapper.selectPlanCountToday(plan));

        plan.setPlanStatus("1");
        record.setPlanAchieve(planMapper.selectPlanCountToday(plan));
        return recordMapper.updateRecord(record);
    }

    @Override
    public int getTotalCount() {
        return recordMapper.selectTotalCount();
    }

    /**
     * 评分系统 满分五分
     * 任务完成度(planProcess) —— 全部完成 + 2分 \ 75% + 1.5分 \ 50% + 1分 \ 25% + 0.5分 \ 0% 0分
     * 每日一万步 —— +1 分
     * 今日消费 —— 消费低于30 +1 分 / 消费低于50 高于30 +0.5 分
     * @return
     */
    public Double score(Record record, RecordAccountVo recordAccountVo){
        // 初始化分数
        double score = 0;

        // 任务完成度打分
        Double planProcess = record.getPlanProcess();
        if (planProcess != null){
            if (planProcess >= 75){
                score += 2;
            }else if(planProcess < 75 && planProcess >= 50){
                score += 1.5;
            }else if(planProcess < 50 && planProcess >= 25){
                score += 1;
            }else if (planProcess < 25 && planProcess > 0){
                score += 0.5;
            }
        }

        // 每日一万步打分
        if (record.getSteps() >= 10000){
            score += 1;
        }

        int expense = recordAccountVo.getExpense().intValue();
        if (expense < 30){
            score += 1;
        }else if (expense < 50){
            score += 0.5;
        }

        return score;
    }

    public List<Record> backRecordList(){
        Record record = new Record();
        record.setStartTime(DateUtil.formatDate(DateUtil.beginOfMonth(new Date())));
        record.setEndTime(DateUtil.formatDate(DateUtil.endOfMonth(new Date())));

        List<Record> recordList = recordMapper.selectRecordList(record);

        return recordList;
    }
}
