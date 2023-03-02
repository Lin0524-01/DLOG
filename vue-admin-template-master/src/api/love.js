import request from '@/utils/request'

export default{
    getLoveDay(){
        return request({
            url: `/love/days`,
            method: 'get'
        })
    },
    addLoverCard(loverCard){
        return request({
            url: `/love/addCard`,
            method: 'post',
            data: loverCard
        })
    },
    getCardList(){
        return request({
            url: `/love/getCardList`,
            method: 'get'
        })
    }
}