package com.moon.dlog.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-16 9:19
 **/
public class TeamSeasonData {
    @ExcelProperty(index = 0)
    private Integer rank;

    @ExcelProperty(index = 1)
    private String teamName;

    @ExcelProperty(index = 2)
    private String hitRate;

    @ExcelProperty(index = 5)
    private String threeHitRate;

    @ExcelProperty(index = 8)
    private String freeHitRate;

    @ExcelProperty(index = 11)
    private String backgroundPerGame;

    @ExcelProperty(index = 14)
    private String assistPerGame;

    @ExcelProperty(index = 15)
    private String mistakePerGame;

    @ExcelProperty(index = 16)
    private String stealPerGame;

    @ExcelProperty(index = 17)
    private String blockPerGame;

    @ExcelProperty(index = 18)
    private String foolPerGame;

    @ExcelProperty(index = 19)
    private String scorePerGame;

    @ExcelProperty(index = 20)
    private Integer games;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getHitRate() {
        return hitRate;
    }

    public void setHitRate(String hitRate) {
        this.hitRate = hitRate;
    }

    public String getThreeHitRate() {
        return threeHitRate;
    }

    public void setThreeHitRate(String threeHitRate) {
        this.threeHitRate = threeHitRate;
    }

    public String getFreeHitRate() {
        return freeHitRate;
    }

    public void setFreeHitRate(String freeHitRate) {
        this.freeHitRate = freeHitRate;
    }

    public String getBackgroundPerGame() {
        return backgroundPerGame;
    }

    public void setBackgroundPerGame(String backgroundPerGame) {
        this.backgroundPerGame = backgroundPerGame;
    }

    public String getAssistPerGame() {
        return assistPerGame;
    }

    public void setAssistPerGame(String assistPerGame) {
        this.assistPerGame = assistPerGame;
    }

    public String getMistakePerGame() {
        return mistakePerGame;
    }

    public void setMistakePerGame(String mistakePerGame) {
        this.mistakePerGame = mistakePerGame;
    }

    public String getStealPerGame() {
        return stealPerGame;
    }

    public void setStealPerGame(String stealPerGame) {
        this.stealPerGame = stealPerGame;
    }

    public String getBlockPerGame() {
        return blockPerGame;
    }

    public void setBlockPerGame(String blockPerGame) {
        this.blockPerGame = blockPerGame;
    }

    public String getFoolPerGame() {
        return foolPerGame;
    }

    public void setFoolPerGame(String foolPerGame) {
        this.foolPerGame = foolPerGame;
    }

    public String getScorePerGame() {
        return scorePerGame;
    }

    public void setScorePerGame(String scorePerGame) {
        this.scorePerGame = scorePerGame;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
