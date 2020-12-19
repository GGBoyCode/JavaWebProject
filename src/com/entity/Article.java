package com.entity;

import java.sql.Timestamp;

public class Article {
    private int id; //文章id
    private String userId;  //用户id
    private String title;   //标题
    private String content; //内容
    private Timestamp publishTime;  //发布时间
    private int goodCount;  //点赞数
    private int collectionCount; //收藏数

    public Article(String userId, String title, String content, Timestamp publishTime, int goodCount, int collectionCount) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.publishTime = publishTime;
        this.goodCount = goodCount;
        this.collectionCount = collectionCount;
    }

    public Article() {
        this(null, null, null, null, 0, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public int getCollection() {
        return collectionCount;
    }

    public void setCollection(int collectionCount) {
        this.collectionCount = collectionCount;
    }
}
