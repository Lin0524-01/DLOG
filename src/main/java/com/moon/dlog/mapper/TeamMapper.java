package com.moon.dlog.mapper;

import com.moon.dlog.domain.Season;
import com.moon.dlog.domain.Team;
import com.moon.dlog.domain.TeamPlayer;
import com.moon.dlog.domain.TeamSeason;
import com.moon.dlog.domain.vo.TeamData;
import com.moon.dlog.domain.vo.TeamPlayerVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-10 23:06
 **/
@Repository
public interface TeamMapper {

    List<Team> selectTeamList(Team team);

    List<String> selectTeamPartitionList();

    TeamData selectTeamGameInfoListCurrentSeason(Long id);

    List<TeamPlayer> selectTeamPlayerList(Long id);

    void insertPlayerInfo(TeamPlayer teamPlayer);

    Long selectCurrentInsertPlayerId();

    void insertTeamPlayer(TeamPlayerVo teamPlayerVo);

    Long selectTeamIdByTeamName(String teamName);

    void updateTeamSeason(TeamSeason teamSeason);

    List<Season> selectSeasonList();
}
