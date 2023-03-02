import request from '@/utils/request'

export default{
    getList(account, pageNum, pageSize) {
        return request({
          url: `/account/list/${pageNum}/${pageSize}`,
          method: 'post',
          data: account
        })
    },
    getBalance() {
        return request({
          url: `/account/balance`,
          method: 'get',
        })
    },
    addAccount(account){
        return request({
            url: `/account/saveConsumeLog`,
            method: 'post',
            data: account
        })
    },
    getIncomeAndExpensePerMonth(){
        return request({
            url: `/account/incomeAndExpenseByMonth`,
            method: 'get'
        })
    },
    editAccountLog(account){
        return request({
            url: `/account/editAccountLog/${id}`,
            method: 'post',
            data: account
        })
    },
    getLineData(){
        return request({
            url: `/account/getDataToLine`,
            method: 'get'
        })
    },
    getExpensePieCurrentMonth(){
        return request({
            url: `/account/getExpensePieCurrentMonth`,
            method: 'get'
        })
    },
    getCurrentIncomeAndExpenseMoney(){
        return request({
            url: `/account/getCurrentMonthIncomeAndConsumePerDay`,
            method: 'get'
        })
    }
}