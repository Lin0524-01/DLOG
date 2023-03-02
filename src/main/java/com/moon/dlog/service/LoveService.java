package com.moon.dlog.service;

import com.moon.dlog.domain.LoverCard;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-23 15:17
 **/
public interface LoveService {
    int getLoveDays();

    int addCard(LoverCard loverCard);

    List<LoverCard> getCardList();
}
