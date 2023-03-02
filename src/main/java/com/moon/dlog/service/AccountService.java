package com.moon.dlog.service;

import com.moon.dlog.domain.Account;
import com.moon.dlog.domain.Record;
import com.moon.dlog.domain.vo.RecordAccountVo;
import com.moon.dlog.domain.vo.Type;

import java.math.BigDecimal;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-21 9:33
 **/
public interface AccountService {
    int saveAccountConsumeLog(Account account);

    /**
     *
     * @return
     */
    BigDecimal getAccountBalance();

    List<Account> getAccountConsumeLog(Account account, int pageNum, int pageSize);

    /**
     * 获取每日消费和收入金额
     * @return
     */
    List<BigDecimal> getIncomeAndExpensePerMonth();

    int editAccount(Long id);

    List<String> getAccountDateList();

    List<BigDecimal> getAccountRemarkList();

    List<String> getIncomeAndExpensePerMonthDateList();

    List<BigDecimal> getPerMonthIncomeList();

    List<String> getCurrentMonthDateList();

    List<BigDecimal> getPerMonthExpenseList();

    RecordAccountVo getIncomeAndExpenseToday(Record record);

    int getTotalCount(Account account);

    List<Account> getExpensePieCurrentMonth();
}
