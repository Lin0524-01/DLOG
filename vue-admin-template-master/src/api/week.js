import request from '@/utils/request'

export default{
    addWeekPlan(weekPlan){
        return request({
            url: `/week/addWeekPlan`,
            method: 'post',
            data: weekPlan
        })
    },
    getWeekList(){
        return request({
            url: `/week/list`,
            method: 'get'
        })
    },
    getWeekPlanListById(id){
        return request({
            url: `/week/getWeekPlanList/${id}`,
            method: 'get'
        })
    },
    getWeekPlanList(){
        return request({
            url: `/week/getWeekPlanList`,
            method: 'get'
        })
    },
    deleteWeekPlanById(id){
        return request({
            url: `/week/removeWeekPlanById/${id}`,
            method: 'get'
        })
    }
}