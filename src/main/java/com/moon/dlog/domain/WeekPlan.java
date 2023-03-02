package com.moon.dlog.domain;

import java.util.Date;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-19 15:56
 **/
public class WeekPlan {
    private Long id;

    private Long weekId;

    private String weekPlanName;

    private Integer progress;

    private Date createTime;

    private String startDate;

    private String endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeekId() {
        return weekId;
    }

    public void setWeekId(Long weekId) {
        this.weekId = weekId;
    }

    public String getWeekPlanName() {
        return weekPlanName;
    }

    public void setWeekPlanName(String weekPlanName) {
        this.weekPlanName = weekPlanName;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
