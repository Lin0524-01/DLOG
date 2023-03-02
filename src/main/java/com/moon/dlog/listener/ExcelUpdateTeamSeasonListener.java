package com.moon.dlog.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.moon.dlog.domain.TeamSeason;
import com.moon.dlog.domain.excel.TeamSeasonData;
import com.moon.dlog.mapper.TeamMapper;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-16 9:28
 **/
public class ExcelUpdateTeamSeasonListener extends AnalysisEventListener<TeamSeasonData> {
    private TeamMapper teamMapper;

    public ExcelUpdateTeamSeasonListener(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

    public ExcelUpdateTeamSeasonListener() {
    }

    @Override
    public void invoke(TeamSeasonData teamSeasonData, AnalysisContext analysisContext) {
        TeamSeason teamSeason = new TeamSeason();

        Long teamId = teamMapper.selectTeamIdByTeamName(teamSeasonData.getTeamName());
        teamSeason.setTeamId(teamId);
        teamSeason.setZoneRanking(teamSeasonData.getRank());
        teamSeason.setGames(teamSeasonData.getGames());
        teamSeason.setAssistPerGame(teamSeasonData.getAssistPerGame());
        teamSeason.setBackboardPerGame(teamSeasonData.getBackgroundPerGame());
        teamSeason.setBlockPerGame(teamSeasonData.getBlockPerGame());
        teamSeason.setFoulPerGame(teamSeasonData.getFoolPerGame());
        teamSeason.setScorePerGame(teamSeasonData.getScorePerGame());
        teamSeason.setMistakePerGame(teamSeasonData.getMistakePerGame());
        teamSeason.setStealPerGame(teamSeasonData.getStealPerGame());
        teamSeason.setFreeHitRate(teamSeasonData.getFreeHitRate());
        teamSeason.setHitRate(teamSeasonData.getHitRate());
        teamSeason.setThreeHitRate(teamSeasonData.getThreeHitRate());

        teamMapper.updateTeamSeason(teamSeason);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
