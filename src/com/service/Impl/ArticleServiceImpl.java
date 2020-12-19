package com.service.Impl;

import com.dao.IArticleDao;
import com.dao.IUserDao;
import com.dao.impl.ArticleDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.entity.Article;
import com.service.IArticleService;

import java.sql.SQLException;
import java.util.List;

public class ArticleServiceImpl implements IArticleService {
    private IUserDao userDao = new UserDaoImpl();
    private IArticleDao articleDao = new ArticleDaoImpl();
    //新增文章
    public boolean addArticle(Article article) throws SQLException {
        if(userDao.SelectByUsername(article.getUserId())) {
            return articleDao.addArticle(article);
        } else {
            return false;
        }
    }

    //删除文章
    public boolean deleteArticle(int id) throws SQLException {
        if(articleDao.existId(id)) {
            return articleDao.deleteArticle(id);
        } else {
            return false;
        }
    }

    //获取文章
    public Article getArticle(int id) throws SQLException {
        if(articleDao.existId(id)) {
            return articleDao.getArticle(id);
        } else {
            return null;
        }
    }

    //获取全部文章
    public List<Article> getAllArticle() throws SQLException {
        return articleDao.getAllArticle();
    }

    //为文章点赞
    public boolean goodArticle(int id) throws SQLException {
        if(articleDao.existId(id)) {
            return articleDao.goodArticle(id);
        } else {
            return false;
        }
    }

    //收藏文章
    public boolean collectArticle(int id) throws SQLException {
        if(articleDao.existId(id)) {
            return articleDao.collectArticle(id);
        } else {
            return false;
        }
    }
}
