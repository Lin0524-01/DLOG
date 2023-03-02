package com.moon.dlog;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DlogApplicationTests {

    @Test
    void contextLoads() {
        DateTime startTime = DateUtil.parse("2023-01-01 00:00:00");
        System.out.println(startTime);
        System.out.println(DateUtil.isExpired(startTime, DateField.HOUR_OF_DAY, 24, DateUtil.parseDate("2023-01-01 11:11:11")));
    }
}
