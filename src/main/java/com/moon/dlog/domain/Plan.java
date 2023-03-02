package com.moon.dlog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-23 22:27
 **/
public class Plan implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long recordId;

    private String planName;

    private List<Date> rangeOfPlanTime;

    private String planTime;

    private String priority;

    private String planStatus;

    private Date createTime;

    private Date finishTime;

    private Long summaryId;

    private String startTime;

    private String endTime;

    private Integer progress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Date> getRangeOfPlanTime() {
        return rangeOfPlanTime;
    }

    public void setRangeOfPlanTime(List<Date> rangeOfPlanTime) {
        this.rangeOfPlanTime = rangeOfPlanTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Long getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Long summaryId) {
        this.summaryId = summaryId;
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

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", planName='" + planName + '\'' +
                ", rangeOfPlanTime=" + rangeOfPlanTime +
                ", planTime='" + planTime + '\'' +
                ", priority='" + priority + '\'' +
                ", planStatus='" + planStatus + '\'' +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
