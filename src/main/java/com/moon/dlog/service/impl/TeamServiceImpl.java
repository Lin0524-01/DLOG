package com.moon.dlog.service.impl;

import com.alibaba.excel.EasyExcel;
import com.moon.dlog.domain.Season;
import com.moon.dlog.domain.Team;
import com.moon.dlog.domain.TeamPlayer;
import com.moon.dlog.domain.excel.PlayerInfoData;
import com.moon.dlog.domain.excel.TeamSeasonData;
import com.moon.dlog.domain.vo.TeamData;
import com.moon.dlog.listener.ExcelInsertPlayInfoListener;
import com.moon.dlog.listener.ExcelUpdateTeamSeasonListener;
import com.moon.dlog.mapper.TeamMapper;
import com.moon.dlog.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-10 23:06
 **/
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<Team> getTeamList(Team team) {
        return teamMapper.selectTeamList(team);
    }

    @Override
    public List<String> getTeamPartitionList() {
        return teamMapper.selectTeamPartitionList();
    }

    @Override
    public TeamData getTeamGameInfoListCurrentSeason(Long id) {
        return teamMapper.selectTeamGameInfoListCurrentSeason(id);
    }

    @Override
    public List<TeamPlayer> getTeamPlayerList(Long id) {
        return teamMapper.selectTeamPlayerList(id);
    }

    @Transactional
    @Override
    public void savePlayerInfo(String filePath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(filePath));
            EasyExcel.read(fis, PlayerInfoData.class, new ExcelInsertPlayInfoListener(teamMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTeamSeasonData(String filePath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(filePath));
            EasyExcel.read(fis, TeamSeasonData.class, new ExcelUpdateTeamSeasonListener(teamMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Season> getSeasonList() {
        return teamMapper.selectSeasonList();
    }
}
