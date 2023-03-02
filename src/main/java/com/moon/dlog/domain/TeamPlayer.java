package com.moon.dlog.domain;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-12 11:44
 **/
public class TeamPlayer {
    private Long id;

    private Integer playerAge;

    private String playerName;

    private Integer playerNumber;

    private String playerRole;

    private String playerBirth;

    private String playerHeight;

    private String playerWeight;

    private String playerCountry;

    private String playerDraft;

    private String playerSalary;

    private String playerContract;

    private String playerAvatar;

    private String playerSchool;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(Integer playerAge) {
        this.playerAge = playerAge;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerBirth() {
        return playerBirth;
    }

    public void setPlayerBirth(String playerBirth) {
        this.playerBirth = playerBirth;
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

    public String getPlayerCountry() {
        return playerCountry;
    }

    public void setPlayerCountry(String playerCountry) {
        this.playerCountry = playerCountry;
    }

    public String getPlayerDraft() {
        return playerDraft;
    }

    public void setPlayerDraft(String playerDraft) {
        this.playerDraft = playerDraft;
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

    public String getPlayerAvatar() {
        return playerAvatar;
    }

    public void setPlayerAvatar(String playerAvatar) {
        this.playerAvatar = playerAvatar;
    }

    public String getPlayerSchool() {
        return playerSchool;
    }

    public void setPlayerSchool(String playerSchool) {
        this.playerSchool = playerSchool;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public String toString() {
        return "TeamPlayer{" +
                "id=" + id +
                ", playerAge=" + playerAge +
                ", playerName='" + playerName + '\'' +
                ", playerNumber=" + playerNumber +
                ", playerRole='" + playerRole + '\'' +
                ", playerBirth='" + playerBirth + '\'' +
                ", playerHeight='" + playerHeight + '\'' +
                ", playerWeight='" + playerWeight + '\'' +
                ", playerCountry='" + playerCountry + '\'' +
                ", playerDraft='" + playerDraft + '\'' +
                ", playerSalary=" + playerSalary +
                ", playerContract='" + playerContract + '\'' +
                ", playerAvatar='" + playerAvatar + '\'' +
                ", playerSchool='" + playerSchool + '\'' +
                '}';
    }
}
