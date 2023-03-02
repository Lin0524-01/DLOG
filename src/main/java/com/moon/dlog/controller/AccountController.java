package com.moon.dlog.controller;

import com.moon.dlog.domain.Account;
import com.moon.dlog.domain.R;
import com.moon.dlog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-21 9:32
 **/
@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 添加收支记录
     * @param account
     * @return
     */
    @PostMapping("/saveConsumeLog")
    public R saveConsumeLog(@RequestBody Account account){
        try {
            accountService.saveAccountConsumeLog(account);
            return R.ok().message("添加成功");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return R.error().message("添加失败，请联系管理员");
    }

    /**
     * 列表分页查询
     * @param account
     * @return
     */
    @PostMapping("/list/{pageNum}/{pageSize}")
    public R list(@RequestBody Account account, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        List<Account> list = accountService.getAccountConsumeLog(account, pageNum, pageSize);
        int total = accountService.getTotalCount(account);
        return R.ok().data("accountList", list).data("total", total);
    }

    /**
     * 查询当前账户余额
     * @return
     */
    @GetMapping("/balance")
    public R balance(){
        BigDecimal balance = accountService.getAccountBalance();
        return R.ok().data("balance", balance);
    }

    /**
     * 查询本月收入和支出金额 首页展示
     * @return
     */
    @GetMapping("/incomeAndExpenseByMonth")
    public R incomeAndExpensePerMonth(){
        List<BigDecimal> result = accountService.getIncomeAndExpensePerMonth();
        return R.ok().data("income", result.get(0)).data("expense", result.get(1));
    }

    /**
     * 修改收支情况  未实现
     * @param id
     * @return
     */
    @PostMapping("/editAccountLog/{id}")
    public R editAccountLog(@PathVariable("id") Long id){
        int result = accountService.editAccount(id);
        return R.ok();
    }

    /**
     * 余额宝收益折线图
     * @return
     */
    @GetMapping("/getDataToLine")
    public R getDataToLine(){
        List<String> dateList = accountService.getAccountDateList();
        List<BigDecimal> moneyList = accountService.getAccountRemarkList();
        return R.ok().data("dateList", dateList).data("moneyList", moneyList);
    }

    /**
     * 得到本月收入、支出——折线图展示
     * @return
     */
    @GetMapping("/getCurrentMonthIncomeAndConsumePerDay")
    public R getCurrentMonthIncomeAndConsumePerDay(){
        List<String> dateList = accountService.getCurrentMonthDateList();
        List<BigDecimal> incomeList = accountService.getPerMonthIncomeList();
        List<BigDecimal> expenseList = accountService.getPerMonthExpenseList();

        return R.ok().data("monthDateList", dateList).data("incomeList", incomeList).data("expenseList", expenseList);
    }

    /**
     * 得到本月消费类型及金额——饼图展示
     * @return
     */
    @GetMapping("/getExpensePieCurrentMonth")
    public R getExpensePieCurrentMonth(){
        List<Account> expenseListPerMonth = accountService.getExpensePieCurrentMonth();
        return R.ok().data("expenseTypeAndMoneyList", expenseListPerMonth);
    }
}
