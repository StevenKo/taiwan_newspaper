package com.taiwan.news.entity;

public class Picture {
    String intro;
    String url;

    public Picture() {
        this("", "");
    }

    public Picture(String url, String intro) {
        this.url = url;
        this.intro = intro;
    }

    public String getUrl() {
        return url;
    }

    public String getIntro() {
        return intro;
    }

}
