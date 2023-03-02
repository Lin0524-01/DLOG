package com.moon.dlog.domain.vo;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-13 16:31
 **/
public class TeamPlayerVo {
    private Long teamId;

    private Long seasonId;

    private Long playerId;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
