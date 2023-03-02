package com.moon.dlog.mapper;

import com.moon.dlog.domain.LoverCard;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-23 15:17
 **/
@Repository
public interface LoveMapper {

    int selectLoveDays();

    int addCard(LoverCard loverCard);

    List<LoverCard> selectCardList();
}
