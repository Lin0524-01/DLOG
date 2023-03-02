package com.moon.dlog.domain;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-10 23:05
 **/
public class Team {
    private Long id;

    private String teamCity;

    private String teamName;

    private String teamRoom;

    private String teamCoach;

    private String teamPartition;

    private String teamLogo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamPartition() {
        return teamPartition;
    }

    public void setTeamPartition(String teamPartition) {
        this.teamPartition = teamPartition;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getTeamRoom() {
        return teamRoom;
    }

    public void setTeamRoom(String teamRoom) {
        this.teamRoom = teamRoom;
    }

    public String getTeamCoach() {
        return teamCoach;
    }

    public void setTeamCoach(String teamCoach) {
        this.teamCoach = teamCoach;
    }
}
