package com.moon.dlog.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-08 20:05
 **/
public class RecordAccountVo {
    private Date recordDate;

    private String type;

    private BigDecimal income;

    private BigDecimal expense;

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }
}
