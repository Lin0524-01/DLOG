package com.moon.dlog.controller;

import com.moon.dlog.domain.R;
import com.moon.dlog.domain.Season;
import com.moon.dlog.domain.Team;
import com.moon.dlog.domain.TeamPlayer;
import com.moon.dlog.domain.vo.TeamData;
import com.moon.dlog.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-10 23:07
 **/
@RestController
@CrossOrigin
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/list")
    public R getTeamList(@RequestBody Team team){
        List<Team> teamList = teamService.getTeamList(team);
        List<String> teamPartitionList = teamService.getTeamPartitionList();

        return R.ok().data("teamList", teamList).data("teamPartitionList", teamPartitionList);
    }

    @GetMapping("/getTeamSeasonData/{id}")
    public R getTeamSeasonData(@PathVariable Long id){
        TeamData teamData = teamService.getTeamGameInfoListCurrentSeason(id);
        return R.ok().data("teamData", teamData);
    }

    @GetMapping("/getTeamPlayerList/{id}")
    public R getTeamPlayerList(@PathVariable Long id){
        List<TeamPlayer> playerList = teamService.getTeamPlayerList(id);
        return R.ok().data("playerList", playerList);
    }

    @PostMapping("/addPlayerInfo")
    public R addPlayerInfo(@RequestParam String filePath){
        teamService.savePlayerInfo(filePath);
        return R.ok();
    }

    @PostMapping("/updateTeamSeasonData")
    public R updateTeamSeasonData(@RequestParam String filePath){
        teamService.updateTeamSeasonData(filePath);
        return R.ok();
    }

    /**
     * 选择器——赛季列表
     * @return
     */
    @GetMapping("/getSeasonList")
    public R getSeasonList(){
        List<Season> seasonList = teamService.getSeasonList();
        return R.ok().data("seasonList", seasonList);
    }
}
