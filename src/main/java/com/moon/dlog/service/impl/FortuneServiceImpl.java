package com.moon.dlog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.moon.dlog.domain.Fortune;
import com.moon.dlog.mapper.FortuneMapper;
import com.moon.dlog.service.FortuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-27 22:33
 **/
@Service
public class FortuneServiceImpl implements FortuneService {
    @Autowired
    private FortuneMapper fortuneMapper;

    @Override
    public void addFortune(Fortune fortune) {
        fortuneMapper.addFortune(fortune);
    }

    @Override
    public List<Fortune> getFortuneList(Fortune fortune) {
        if (fortune.getStartTime() != null && fortune.getEndTime() != null){
            return fortuneMapper.selectFortuneList(fortune);
        }
        fortune.setStartTime(DateUtil.formatDate(DateUtil.beginOfMonth(new Date())));
        fortune.setEndTime(DateUtil.formatDate(DateUtil.endOfMonth(new Date())));
        return fortuneMapper.selectFortuneList(fortune);
    }
}
