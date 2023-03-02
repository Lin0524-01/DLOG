import request from '@/utils/request'

export default{
    getFortuneList(fortune){
        return request({
            url: `/fortune/list`,
            method: 'post',
            data: fortune
        })
    },
    addFortune(fortune){
        return request({
            url: `/fortune/addFortune`,
            method: 'post',
            data: fortune
        })
    }
}