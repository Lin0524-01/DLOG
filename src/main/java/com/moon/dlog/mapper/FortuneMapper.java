package com.moon.dlog.mapper;

import com.moon.dlog.domain.Fortune;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-27 22:34
 **/
@Repository
public interface FortuneMapper {
    int addFortune(Fortune fortune);

    List<Fortune> selectFortuneList(Fortune fortune);
}
