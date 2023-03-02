package com.moon.dlog.service;

import com.moon.dlog.domain.Fortune;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-27 22:33
 **/
public interface FortuneService {
    void addFortune(Fortune fortune);

    List<Fortune> getFortuneList(Fortune fortune);
}
