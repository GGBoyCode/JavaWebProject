package com.entity;

import java.sql.Timestamp;

public class Comment {
    private int id; //评论id
    private String userId; //用户id
    private int articleId;  //文章id
    private String content; //评论内容
    private Timestamp publishTime;  //发布时间
    private int goodCount;  //点赞数

    public Comment() {
        this(null, 0, null, null, 0);
    }

    public Comment(String userId, int articleId, String content, Timestamp publishTime, int goodCount) {
        this.userId = userId;
        this.articleId = articleId;
        this.content = content;
        this.publishTime = publishTime;
        this.goodCount = goodCount;
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

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
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
}
