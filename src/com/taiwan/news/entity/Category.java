package com.taiwan.news.entity;

public class Category {
    int    id;
    String name;

    public Category() {
        this(-1, "");
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCateName() {
        return name;
    }

    public void setCateName(String name) {
        this.name = name;
    }

}
