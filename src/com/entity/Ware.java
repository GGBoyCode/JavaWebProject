package com.entity;

import java.sql.Timestamp;

public class Ware {
    private int id; //商品id
    private String name;    //商品名称
    private String url; //商品图片地址
    private float price;  //商品价格
    private String description; //商品描述
    private int type;   //出售情况 0未出售 1已出售
    private Timestamp saleTime; //出售时间

    public Ware() {this(0, null, null, 0, null);}

    public Ware(int id, String name, String url, float price, String description) {
        this.id = id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Timestamp saleTime) {
        this.saleTime = saleTime;
    }
}
