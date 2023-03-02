package com.moon.dlog.domain;

import java.util.Date;

/**
 * @version 1.0
 * @Description 每日记录
 * @Author linyl
 * @Date 2023-01-08 14:33
 **/
public class Record {
    private Long id;

    private Date recordDate;

    private Integer steps;

    private Integer planSummary;

    private Integer planAchieve;

    private Double planProcess;

    private String weather;

    private String mood;

    private Double score;

    private String note;

    private String startTime;

    private String endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getPlanSummary() {
        return planSummary;
    }

    public void setPlanSummary(Integer planSummary) {
        this.planSummary = planSummary;
    }

    public Integer getPlanAchieve() {
        return planAchieve;
    }

    public void setPlanAchieve(Integer planAchieve) {
        this.planAchieve = planAchieve;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getPlanProcess() {
        return planProcess;
    }

    public void setPlanProcess(Double planProcess) {
        this.planProcess = planProcess;
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
