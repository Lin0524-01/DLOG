package com.moon.dlog.domain;

import java.util.Date;

/**
 * @version 1.0
 * @Description 星座运势
 * @Author linyl
 * @Date 2023-01-27 22:32
 **/
public class Fortune {
    private Long id;

    private Date fortuneDate;

    private String fortuneContent;

    private String startTime;

    private String endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFortuneDate() {
        return fortuneDate;
    }

    public void setFortuneDate(Date fortuneDate) {
        this.fortuneDate = fortuneDate;
    }

    public String getFortuneContent() {
        return fortuneContent;
    }

    public void setFortuneContent(String fortuneContent) {
        this.fortuneContent = fortuneContent;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
