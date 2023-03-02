package com.moon.dlog.listener;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.moon.dlog.domain.Team;
import com.moon.dlog.domain.TeamPlayer;
import com.moon.dlog.domain.excel.PlayerInfoData;
import com.moon.dlog.domain.vo.TeamPlayerVo;
import com.moon.dlog.mapper.TeamMapper;

import java.util.List;

/**
 * @version 1.0
 * @Description easy-excel实现将excel文件输入插入到数据中
 * @Author linyl
 * @Date 2023-01-12 22:05
 **/
public class ExcelInsertPlayInfoListener extends AnalysisEventListener<PlayerInfoData> {
    private TeamMapper teamMapper;

    public ExcelInsertPlayInfoListener(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

    public ExcelInsertPlayInfoListener() {
    }

    /**
     * EasyExcel会一行一行进行读取，每一条解析数据都会执行invoke方法
     * @param playerInfoData
     * @param analysisContext
     */
    @Override
    public void invoke(PlayerInfoData playerInfoData, AnalysisContext analysisContext) {
        TeamPlayer teamPlayer = new TeamPlayer();
        // 处理playerRole 在excel存储形式为role + number
        String playerRole = playerInfoData.getPlayerRole();
        String playerNumber = playerRole.substring(playerRole.indexOf("（") + 1, playerRole.length() - 2);
        playerRole = playerRole.substring(0, playerRole.indexOf("（"));

        if(playerInfoData.getPlayerDraft() == null){
            playerInfoData.setPlayerDraft("落选秀");
        }

        // 格式化薪资 获取数字内容
        String playerSalary = playerInfoData.getPlayerSalary();
        if (playerSalary != null){
            playerSalary = playerSalary.substring(0, playerSalary.indexOf("万"));
        }

        teamPlayer.setPlayerNumber(Integer.valueOf(playerNumber));
        teamPlayer.setPlayerName(playerInfoData.getPlayerName());
        teamPlayer.setPlayerSchool(playerInfoData.getPlayerSchool());
        teamPlayer.setPlayerRole(playerRole);
        teamPlayer.setPlayerBirth(playerInfoData.getPlayerBirth());
        teamPlayer.setPlayerHeight(playerInfoData.getPlayerHeight());
        teamPlayer.setPlayerWeight(playerInfoData.getPlayerWeight());
        teamPlayer.setPlayerCountry(playerInfoData.getPlayerCountry());
        teamPlayer.setPlayerDraft(playerInfoData.getPlayerDraft());
        teamPlayer.setPlayerSalary(playerSalary);
        teamPlayer.setPlayerContract(playerInfoData.getPlayerContract());
        teamPlayer.setPlayerAge(DateUtil.ageOfNow(DateUtil.parse(playerInfoData.getPlayerBirth())));
        teamMapper.insertPlayerInfo(teamPlayer);

        Team team = backTeamCurrentPlayer(playerInfoData.getPlayerTeam());
        Long playerId = teamMapper.selectCurrentInsertPlayerId();

        TeamPlayerVo teamPlayerVo = new TeamPlayerVo();
        teamPlayerVo.setTeamId(team.getId());
        teamPlayerVo.setSeasonId(1L);
        teamPlayerVo.setPlayerId(playerId);
        teamMapper.insertTeamPlayer(teamPlayerVo);
    }

    /**
     * 全部解析完执行的方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * 返回Team id
     * @param teamName
     * @return
     */
    public Team backTeamCurrentPlayer(String teamName){
        if (teamName == null){
            return new Team();
        }
        List<Team> teamList = teamMapper.selectTeamList(new Team());
        for (Team team : teamList) {
            if (teamName.equals(team.getTeamCity() + team.getTeamName())) {
                return team;
            }
        }
        return new Team();
    }
}
