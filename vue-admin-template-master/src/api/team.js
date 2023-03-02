import request from '@/utils/request'

export default{
    getTeamList(team){
        return request({
            url: `/team/list`,
            method: 'post',
            data: team
        })
    },
    getTeamSeason(id){
        return request({
            url: `/team/getTeamSeasonData/${id}`,
            method: 'get'
        })
    },
    getTeamPlayerList(id){
        return request({
            url: `/team/getTeamPlayerList/${id}`,
            method: 'get'
        })
    },
    getSeasonList(){
        return request({
            url: `/team/getSeasonList`,
            method: 'get'
        })
    }
}