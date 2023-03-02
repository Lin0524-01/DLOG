package com.moon.dlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.moon.dlog.mapper")
@EnableScheduling
public class DlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlogApplication.class, args);
    }

}
