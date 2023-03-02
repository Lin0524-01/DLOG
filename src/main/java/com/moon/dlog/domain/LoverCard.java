package com.moon.dlog.domain;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-25 21:48
 **/
public class LoverCard {
    private Long id;

    private String cardTitle;

    private String cardContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardContent() {
        return cardContent;
    }

    public void setCardContent(String cardContent) {
        this.cardContent = cardContent;
    }
}
