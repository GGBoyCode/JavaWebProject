package com.entity;

//轮播图实体类
public class Carousel {
    private int id; //轮播图id
    private String url; //轮播图图片相对地址

    public Carousel(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public Carousel(String url) {
        this(0, url);
    }

    public Carousel() { this(0, null); }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
