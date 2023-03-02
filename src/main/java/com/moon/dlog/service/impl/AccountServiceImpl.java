package com.moon.dlog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.moon.dlog.domain.Account;
import com.moon.dlog.domain.Record;
import com.moon.dlog.domain.vo.RecordAccountVo;
import com.moon.dlog.mapper.AccountMapper;
import com.moon.dlog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-21 9:33
 **/
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 添加账户消费记录
     * @param account
     * @return
     */
    @Override
    public int saveAccountConsumeLog(Account account) {
        BigDecimal total = accountMapper.selectNewRow().getTotal();
        account.setCreateTime(new Date());
        // 判断收支，计算出账户余额
        if ("0".equals(account.getType())){
            account.setTotal(total.subtract(account.getMoney()));
        }else if("1".equals(account.getType())){
            account.setTotal(total.add(account.getMoney()));
        }
        return accountMapper.insertAccount(account);
    }

    /**
     * 得到账户余额 最后一条记录total
     * @return
     */
    @Override
    public BigDecimal getAccountBalance() {
        return accountMapper.selectNewRow().getTotal();
    }

    /**
     * 得到账户记录列表
     * @param account
     * @return
     */
    @Override
    public List<Account> getAccountConsumeLog(Account account, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return accountMapper.selectAccountList(account);
    }

    /**
     * 得到本月支出和收入金额——用于饼图展示
     * @return 将收入和支出金额存入列表List<BigDecimal>
     */
    @Override
    public List<BigDecimal> getIncomeAndExpensePerMonth() {
        BigDecimal allIncome = new BigDecimal(0);
        BigDecimal allExpense = new BigDecimal(0);

        Account account = new Account();
        account.setStartTime(DateUtil.formatDate(DateUtil.beginOfMonth(new Date())));
        account.setEndTime(DateUtil.formatDate(DateUtil.endOfMonth(new Date())));

        // 得到支出列表
        account.setType("0");
        List<Account> expenseList = accountMapper.selectAccountList(account);
        for (Account expenseAccount : expenseList) {
            allExpense = allExpense.add(expenseAccount.getMoney());
        }
        // 得到消费列表
        account.setType("1");
        List<Account> incomeList = accountMapper.selectAccountList(account);
        for (Account incomeAccount : incomeList) {
            allIncome = allIncome.add(incomeAccount.getMoney());
        }

        List<BigDecimal> result = new ArrayList<>();
        result.add(allIncome);
        result.add(allExpense);

        return result;
    }

    @Override
    public int editAccount(Long id) {
        return accountMapper.updateAccountById(id);
    }

    /**
     * 得到本月日期列表——余额宝收益折线图 x轴
     * @return
     */
    @Override
    public List<String> getAccountDateList() {
        List<Account> accountList = accountMapper.selectAccountList(backAccount(new Date(), "余额宝收益"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        List<String> dateList = new ArrayList<>();
        for (int i = accountList.size() - 1; i >= 0; i--) {
            dateList.add(simpleDateFormat.format(accountList.get(i).getCreateTime()));
        }
        return dateList;
    }

    /**
     * 得到余额宝收益金额列表
     * @return
     */
    @Override
    public List<BigDecimal> getAccountRemarkList() {
        List<BigDecimal> moneyList = new ArrayList<>();
        List<Account> accountList = accountMapper.selectAccountList(backAccount(new Date(), "余额宝收益"));

        for (int i = accountList.size() - 1; i >= 0; i--) {
            moneyList.add(accountList.get(i).getMoney());
        }
        return moneyList;
    }

    /**
     * 每个月收入和消费日期列表
     * @return
     */
    @Override
    public List<String> getIncomeAndExpensePerMonthDateList() {
        Account account = backIncomeAccount(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        List<String> dateList = new ArrayList<>();
        List<Account> accountList = accountMapper.selectAccountList(account);
        for (int i = accountList.size() - 1; i >= 0; i--) {
            dateList.add(simpleDateFormat.format(accountList.get(i).getCreateTime()));
        }
        return dateList;
    }


    @Override
    public List<BigDecimal> getPerMonthIncomeList() {
        List<BigDecimal> resultList = getCurrentMoneyIncomeOrExpenseList("1");

        return resultList;
    }

    @Override
    public List<BigDecimal> getPerMonthExpenseList() {
        List<BigDecimal> resultList = getCurrentMoneyIncomeOrExpenseList("0");
        return resultList;
    }

    /**
     * 得到今日收入和支出金额 封装了Vo
     * @param record
     * @return
     */
    @Override
    public RecordAccountVo getIncomeAndExpenseToday(Record record) {
        RecordAccountVo recordAccountVo = new RecordAccountVo();
        BigDecimal expenseMoney = new BigDecimal(0);
        BigDecimal incomeMoney = new BigDecimal(0);
        recordAccountVo.setRecordDate(record.getRecordDate());
        // 得到今日支出列表
        recordAccountVo.setType("0");
        List<Account> expenseList = accountMapper.selectIncomeAndExpenseToday(recordAccountVo);
        for (Account expense : expenseList) {
            expenseMoney = expenseMoney.add(expense.getMoney());
        }
        // 得到今日收入列表
        recordAccountVo.setType("1");
        List<Account> incomeList = accountMapper.selectIncomeAndExpenseToday(recordAccountVo);
        for (Account income : incomeList) {
            incomeMoney = incomeMoney.add(income.getMoney());
        }

        recordAccountVo.setIncome(incomeMoney);
        recordAccountVo.setExpense(expenseMoney);
        return recordAccountVo;
    }

    @Override
    public int getTotalCount(Account account) {
        return accountMapper.selectTotalCount(account);
    }

    @Override
    public List<Account> getExpensePieCurrentMonth() {
        Account account = new Account();
        account.setStartTime(DateUtil.formatDate(DateUtil.beginOfMonth(new Date())));
        account.setEndTime(DateUtil.formatDate(DateUtil.endOfMonth(new Date())));
        List<Account> accountList = accountMapper.selectExpensePieDataCurrentMonth(account);

//        List<Account> expenseList = new ArrayList<>();
//        // 消费备注  方便循环进行判断
//        String expenseRemark = accountList.get(0).getRemark();
//        // 消费金额  方便xun
//        BigDecimal expenseMoney = new BigDecimal(0);
//        for (Account expense : accountList) {
//            Account expenseLog = new Account();
//            if (expense.getRemark().equals(expenseRemark)){
//                expenseMoney = expenseMoney.add(expense.getMoney());
//            }else {
//                expenseLog.setRemark(expenseRemark);
//                expenseLog.setMoney(expenseMoney);
//                expenseList.add(expenseLog);
//                expenseRemark = expense.getRemark();
//                expenseMoney = expenseMoney.subtract(expenseMoney).add(expense.getMoney());
//            }
//        }
        return accountList;
    }

    @Override
    public List<String> getCurrentMonthDateList() {
        // 获取本月收入、支出日期
        Account account = new Account();
        account.setStartTime(DateUtil.formatDate(DateUtil.beginOfMonth(new Date())));
        account.setEndTime(DateUtil.formatDate(DateUtil.endOfMonth(new Date())));
        List<Date> dateList = accountMapper.selectCurrentMonthDateList(account);
        List<String> strDateList = new ArrayList<>();
        for (Date date : dateList) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            strDateList.add(dateFormat.format(date));
        }
        return strDateList;
    }

    /**
     * 抽取公共方法 设置日期格式赋值给startTime和endTime 用于得到本月余额宝收益的日期列表和金额列表
     * @param date
     * @param remark
     * @return account 封装好后的查询条件
     */
    public Account backAccount(Date date, String remark){
        Account account = new Account();
        account.setRemark(remark);
        account.setStartTime(DateUtil.formatDate(DateUtil.beginOfMonth(date)));
        account.setEndTime(DateUtil.formatDate(DateUtil.endOfMonth(date)));

        return account;
    }

    /**
     * 抽取公共方法 设置日期格式赋值给startTime和endTime 用于得到本月收入和支出的日期列表和金额列表
     * @param date
     * @return
     */
    public Account backIncomeAccount(Date date){
        Account account = new Account();
        account.setType("0");
        account.setStartTime(DateUtil.formatDate(DateUtil.beginOfMonth(date)));
        account.setEndTime(DateUtil.formatDate(DateUtil.endOfMonth(date)));

        return account;
    }

    public List<BigDecimal> getCurrentMoneyIncomeOrExpenseList(String type){
        List<BigDecimal> resultList = new ArrayList<>();
        List<String> dateList = getCurrentMonthDateList();
        Account account = new Account();

        for (String date : dateList) {
            BigDecimal expenseMoney = new BigDecimal(0);
            account.setType(type);
            account.setStartTime(date + " 00:00:00");
            account.setEndTime(date + " 23:59:59");
            List<Account> accountList = accountMapper.selectAccountList(account);
            for (Account result : accountList) {
                expenseMoney = expenseMoney.add(result.getMoney());
            }
            resultList.add(expenseMoney);
        }

        return resultList;
    }
}
