package com.moon.dlog.domain;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-26 18:07
 **/
public class Season {
    private Long id;

    private String seasonName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
}
