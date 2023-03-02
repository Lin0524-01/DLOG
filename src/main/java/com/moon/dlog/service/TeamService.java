package com.moon.dlog.service;

import com.moon.dlog.domain.Season;
import com.moon.dlog.domain.Team;
import com.moon.dlog.domain.TeamPlayer;
import com.moon.dlog.domain.vo.TeamData;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-10 23:06
 **/
public interface TeamService {
    List<Team> getTeamList(Team team);

    List<String> getTeamPartitionList();

    TeamData getTeamGameInfoListCurrentSeason(Long id);

    List<TeamPlayer> getTeamPlayerList(Long id);

    void savePlayerInfo(String filePath);

    void updateTeamSeasonData(String filePath);

    List<Season> getSeasonList();
}
