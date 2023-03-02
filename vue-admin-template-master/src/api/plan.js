import request from '@/utils/request'

export default{
    getDateFormSpringCalling(){
        return request({
            url: `/plan/timeFormSpringCalling`,
            method: 'get'
        })
    },
    addPlan(plan){
        return request({
            url: `/plan/addPlan`,
            method: 'post',
            data: plan
        })
    },
    getPlanList(plan){
        return request({
            url: `/plan/list`,
            method: 'post',
            data: plan
        })
    },
    changePlanStatusOfFinished(id){
        return request({
            url: `/plan/finishPlan/${id}`,
            method: 'get'
        })
    },
    findPlanById(id){
        return request({
            url: `/plan/findPlanById/${id}`,
            method: 'get'
        })
    },
    deletePlanById(id){
        return request({
            url: `/plan/deletePlanById/${id}`,
            method: 'get'
        })
    },
    getRandomOne(){
        return request({
            url: `/plan/randomSelectOne`,
            method: 'get'
        })
    }
}