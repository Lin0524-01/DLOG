package com.moon.dlog.mapper;

import com.moon.dlog.domain.Account;
import com.moon.dlog.domain.vo.RecordAccountVo;
import com.moon.dlog.domain.vo.Type;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-21 9:33
 **/
@Repository
public interface AccountMapper {

    int insertAccount(Account account);

    List<Account> selectAccountList(Account account);

    Account selectNewRow();

    int updateAccountById(Long id);

    List<BigDecimal> selectCurrentMonthIncomeList(Account backAccount);

    List<Date> selectCurrentMonthDateList(Account account);

    List<Account> selectIncomeAndExpenseToday(RecordAccountVo recordAccountVo);

    int selectTotalCount(Account account);

    List<Account> selectExpensePieDataCurrentMonth(Account account);
}
