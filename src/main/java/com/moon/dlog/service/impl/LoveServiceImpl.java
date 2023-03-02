package com.moon.dlog.service.impl;

import com.moon.dlog.domain.LoverCard;
import com.moon.dlog.mapper.LoveMapper;
import com.moon.dlog.service.LoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-23 15:17
 **/
@Service
public class LoveServiceImpl implements LoveService {
    @Autowired
    private LoveMapper loveMapper;

    @Override
    public int getLoveDays() {
        return loveMapper.selectLoveDays();
    }

    @Override
    public int addCard(LoverCard loverCard) {
        return loveMapper.addCard(loverCard);
    }

    @Override
    public List<LoverCard> getCardList() {
        return loveMapper.selectCardList();
    }
}
