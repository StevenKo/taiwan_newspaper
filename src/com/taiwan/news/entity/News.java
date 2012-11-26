package com.taiwan.news.entity;

import java.util.ArrayList;
import java.util.Date;

public class News {
    private int               id;
    private String            source;
    private ArrayList<String> pictures_url;
    private String            content;
    private int               category_id;
    private Date              releaseTime;
    private final String      categoryName;

    public News() {
        this(-1, "", null, "", -1, new Date(), "");
    }

    public News(int id, String source, ArrayList<String> pictures_url, String content, int category_id, Date releaseTime, String categoryName) {
        this.id = id;
        this.source = source;
        this.pictures_url = pictures_url;
        this.content = content;
        this.category_id = category_id;
        this.releaseTime = releaseTime;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ArrayList<String> getPicturesUrl() {
        return pictures_url;
    }

    public void setPicturesUrl(ArrayList<String> pictures_url) {
        this.pictures_url = pictures_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date time) {
        this.releaseTime = time;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
