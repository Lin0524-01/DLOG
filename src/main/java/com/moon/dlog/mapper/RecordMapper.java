package com.moon.dlog.mapper;

import com.moon.dlog.domain.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-08 14:36
 **/
@Repository
public interface RecordMapper {
    int addRecord(Record record);

    List<Record> selectRecordList(Record record);

    Record selectRecordById(Long id);

    int updateRecord(Record record);

    int deleteRecordById(Long id);

    int selectReachStandardCurrentYearDays(String date);

    int selectEndDays(String date);

    int selectTotalCount();
}
