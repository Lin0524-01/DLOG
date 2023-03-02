package com.moon.dlog.domain;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-11 21:23
 **/
public class TeamSeason {
    private Long id;

    private Long teamId;

    private Long seasonId;

    private String zone;

    private Integer zoneRanking;

    private Integer scoreRanking;

    private Integer assistRanking;

    private Integer backboardRanking;

    private Integer competitorScoreRanking;

    private String scorePerGame;

    private String backboardPerGame;

    private String assistPerGame;

    private String stealPerGame;

    private String blockPerGame;

    private String mistakePerGame;

    private String foulPerGame;

    private String threeHitRate;

    private String freeHitRate;

    private String hitRate;

    private Integer games;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getZoneRanking() {
        return zoneRanking;
    }

    public void setZoneRanking(Integer zoneRanking) {
        this.zoneRanking = zoneRanking;
    }

    public Integer getScoreRanking() {
        return scoreRanking;
    }

    public void setScoreRanking(Integer scoreRanking) {
        this.scoreRanking = scoreRanking;
    }

    public Integer getAssistRanking() {
        return assistRanking;
    }

    public void setAssistRanking(Integer assistRanking) {
        this.assistRanking = assistRanking;
    }

    public Integer getBackboardRanking() {
        return backboardRanking;
    }

    public void setBackboardRanking(Integer backboardRanking) {
        this.backboardRanking = backboardRanking;
    }

    public Integer getCompetitorScoreRanking() {
        return competitorScoreRanking;
    }

    public void setCompetitorScoreRanking(Integer competitorScoreRanking) {
        this.competitorScoreRanking = competitorScoreRanking;
    }

    public String getScorePerGame() {
        return scorePerGame;
    }

    public void setScorePerGame(String scorePerGame) {
        this.scorePerGame = scorePerGame;
    }

    public String getBackboardPerGame() {
        return backboardPerGame;
    }

    public void setBackboardPerGame(String backboardPerGame) {
        this.backboardPerGame = backboardPerGame;
    }

    public String getAssistPerGame() {
        return assistPerGame;
    }

    public void setAssistPerGame(String assistPerGame) {
        this.assistPerGame = assistPerGame;
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

    public String getMistakePerGame() {
        return mistakePerGame;
    }

    public void setMistakePerGame(String mistakePerGame) {
        this.mistakePerGame = mistakePerGame;
    }

    public String getFoulPerGame() {
        return foulPerGame;
    }

    public void setFoulPerGame(String foulPerGame) {
        this.foulPerGame = foulPerGame;
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

    public String getHitRate() {
        return hitRate;
    }

    public void setHitRate(String hitRate) {
        this.hitRate = hitRate;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }
}
