package com.moon.dlog.domain;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-28 22:42
 **/
public class Dictum {
    private Long id;

    private String dictum_text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictum_text() {
        return dictum_text;
    }

    public void setDictum_text(String dictum_text) {
        this.dictum_text = dictum_text;
    }
}
