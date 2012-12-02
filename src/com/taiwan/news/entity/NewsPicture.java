package com.taiwan.news.entity;

public class NewsPicture {
    String intro;
    String url;

    public NewsPicture() {
        this("", "");
    }

    public NewsPicture(String url, String intro) {
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
