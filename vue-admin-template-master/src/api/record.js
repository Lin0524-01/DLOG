import request from '@/utils/request'

export default{
    getRecordList(record, page, pageSize){
        return request({
            url: `/record/list/${page}/${pageSize}`,
            method: 'post',
            data: record
        })
    },
    getRecordById(id){
        return request({
            url: `/record/getRecordById/${id}`,
            method: 'get'
        })
    },
    getIncomeAndExpenseToday(record){
        return request({
            url: `/record/getIncomeAndExpenseToday`,
            method: 'post',
            data: record
        })
    },
    changeScore(record){
        return request({
            url: `/record/updateScore`,
            method: 'post',
            data: record
        })
    },
    deleteRecord(id){
        return request({
            url: `/record/removeRecordById/${id}`,
            method: 'get'
        })
    },
    getTotalDaysCurrentYear(){
        return request({
            url: `/record/getReachStandardCurrentYearDays`,
            method: 'get'
        })
    },
    addRecord(record){
        return request({
            url: `/record/addRecord`,
            method: 'post',
            data: record
        })
    },
    syncPlanToRecord(record){
        return request({
            url: `/record/syncPlan`,
            method: 'post',
            data: record
        })
    },
    getCurrentMonthPlanAchieveStatus(){
        return request({
            url: `/record/getCurrentMonthPlanAchieveStatus`,
            method: 'get'
        })
    }
}