package com.dao;

import com.entity.Article;

import java.sql.SQLException;
import java.util.List;

public interface IArticleDao {
    //新增文章
    public boolean addArticle(Article article) throws SQLException;
    //删除文章
    public boolean deleteArticle(int id) throws SQLException;
    //获取文章
    public Article getArticle(int id) throws SQLException;
    //获取全部文章
    public List<Article> getAllArticle() throws SQLException;
    //查询文章是否存在
    public boolean existId(int id) throws SQLException;
    //为文章点赞
    public boolean goodArticle(int id) throws SQLException;
    //收藏文章
    public boolean collectArticle(int id) throws SQLException;
}
