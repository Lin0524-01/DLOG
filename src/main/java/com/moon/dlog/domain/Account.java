package com.moon.dlog.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-21 9:27
 **/
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private BigDecimal money;

    private String type;

    private BigDecimal total;

    private String remark;

    private Date createTime;

    private String startTime;

    private String endTime;

    private int page;

    private int pageSize;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", money=" + money +
                ", type='" + type + '\'' +
                ", total=" + total +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
