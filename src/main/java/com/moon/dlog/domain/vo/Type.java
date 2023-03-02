package com.moon.dlog.domain.vo;

import java.math.BigDecimal;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-03 19:38
 **/
public class Type {
    private String remark;

    private int count;

    private BigDecimal money;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
