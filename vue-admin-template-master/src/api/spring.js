import request from '@/utils/request'

export default{
    getDateFormSpringCalling(){
        return request({
            url: `/plan/timeFormSpringCalling`,
            method: 'get'
        })
    },
    addCompany(company){
        return request({
            url: `/company/addCompany`,
            method: 'post',
            data: company
        })
    },
    getCompanyList(company){
        return request({
            url: `/company/list`,
            method: 'post',
            data: company
        })
    },
    changeStatus(company){
        return request({
            url: `/company/changeStatus`,
            method: 'post',
            data: company
        })
    },
    getCompanyById(id){
        return request({
            url: `/company/getCompanyById/${id}`,
            method: 'get'
        })
    },
    getIndexData(company){
        return request({
            url: `/company/getIndexData`,
            method: 'post',
            data: company
        })
    },
    getPieData(){
        return request({
            url: `/company/getPostStatusMapToPieShow`,
            method: 'get'
        })
    }
}