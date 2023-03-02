package com.moon.dlog.service;

import com.github.pagehelper.PageInfo;
import com.moon.dlog.domain.Record;
import com.moon.dlog.domain.vo.RecordAccountVo;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-08 14:35
 **/
public interface RecordService {
    int saveRecord(Record record);

    List<Record> getRecordList(Record record, int pageNum, int pageSize);

    Record getRecordById(Long id);

    int updateScore(Record record, RecordAccountVo recordAccountVo);

    int removeRecordById(Long id);

    int getReachStandardCurrentYearDays();

    int getEndDaysCurrentYear();

    List<String> getCurrentMonthDateList();

    List<Integer> getCurrentMonthPlanSummaryList();

    List<Integer> getCurrentMonthPlanAchieveList();

    int syncPlanStatus(Record record);

    int getTotalCount();
}
