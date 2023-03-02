package com.moon.dlog.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-13 9:57
 **/
public class PlayerInfoData {
    @ExcelProperty(index = 0)
    private String playerName;

    @ExcelProperty(index = 1)
    private String playerRole;

    @ExcelProperty(index = 2)
    private String playerHeight;

    @ExcelProperty(index = 3)
    private String playerWeight;

    @ExcelProperty(index = 4)
    private String playerBirth;

    @ExcelProperty(index = 5)
    private String playerTeam;

    @ExcelProperty(index = 6)
    private String playerSchool;

    @ExcelProperty(index = 7)
    private String playerDraft;

    @ExcelProperty(index = 8)
    private String playerCountry;

    @ExcelProperty(index = 9)
    private String playerSalary;

    @ExcelProperty(index = 10)
    private String playerContract;

    public String getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public String getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(String playerHeight) {
        this.playerHeight = playerHeight;
    }

    public String getPlayerWeight() {
        return playerWeight;
    }

    public void setPlayerWeight(String playerWeight) {
        this.playerWeight = playerWeight;
    }

    public String getPlayerBirth() {
        return playerBirth;
    }

    public void setPlayerBirth(String playerBirth) {
        this.playerBirth = playerBirth;
    }

    public String getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(String playerTeam) {
        this.playerTeam = playerTeam;
    }

    public String getPlayerSchool() {
        return playerSchool;
    }

    public void setPlayerSchool(String playerSchool) {
        this.playerSchool = playerSchool;
    }

    public String getPlayerDraft() {
        return playerDraft;
    }

    public void setPlayerDraft(String playerDraft) {
        this.playerDraft = playerDraft;
    }

    public String getPlayerCountry() {
        return playerCountry;
    }

    public void setPlayerCountry(String playerCountry) {
        this.playerCountry = playerCountry;
    }

    public String getPlayerSalary() {
        return playerSalary;
    }

    public void setPlayerSalary(String playerSalary) {
        this.playerSalary = playerSalary;
    }

    public String getPlayerContract() {
        return playerContract;
    }

    public void setPlayerContract(String playerContract) {
        this.playerContract = playerContract;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
