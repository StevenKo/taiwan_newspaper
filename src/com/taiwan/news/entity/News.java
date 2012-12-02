package com.taiwan.news.entity;

import java.util.ArrayList;
import java.util.Date;

public class News {
    private int                id;
    private String             source;
    private ArrayList<NewsPicture> pictures;
    private String             content;
    private int                category_id;
    private Date               releaseTime;
    private final String       categoryName;
    private final String       title;

    public News() {
        this(-1, "", null, "", -1, new Date(), "", "");
    }

    public News(int id, String source, ArrayList<NewsPicture> pictures, String content, int category_id, Date releaseTime, String categoryName, String title) {
        this.id = id;
        this.source = source;
        this.pictures = pictures;
        this.content = content;
        this.category_id = category_id;
        this.releaseTime = releaseTime;
        this.categoryName = categoryName;
        this.title = title;
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

    public ArrayList<NewsPicture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<NewsPicture> pictures) {
        this.pictures = pictures;
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

    public String getTitle() {
        return title;
    }
}
