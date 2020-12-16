package com.entity;

public class Ware {
    private int id; //商品id
    private String name;    //商品名称
    private String url; //商品图片地址
    private int price;  //商品价格
    private String description; //商品描述

    public Ware() {this(null, null, 0, null);}

    public Ware(String name, String url, int price, String description) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
