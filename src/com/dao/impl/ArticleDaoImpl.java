package com.dao.impl;

import com.dao.IArticleDao;
import com.entity.Article;
import com.util.DBCP;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ArticleDaoImpl implements IArticleDao {
    //新增文章
    public boolean addArticle(Article article) throws SQLException {
        String sql = "insert into article(userid, title, content, publishtime, goodcount, collectioncount) values(?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]{article.getUserId(), article.getTitle(), article.getContent(), article.getPublishTime(), article.getGoodCount(), article.getCollection()};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, params);

        return count > 0;
    }

    //删除文章
    public boolean deleteArticle(int id) throws SQLException {
        String sql = "delete from article where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, id);

        return count > 0;
    }

    //获取文章详情
    public Article getArticle(int id) throws SQLException {
        String sql = "select * from article where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);

        return queryRunner.query(sql, new BeanHandler<Article>(Article.class), id);
    }

    //获取全部文章
    public List<Article> getAllArticle() throws SQLException {
        String sql = "select * from article";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);

        return queryRunner.query(sql, new BeanListHandler<>(Article.class));
    }

    //查询文章是否存在
    public boolean existId(int id) throws SQLException {
        String sql = "select count(*) from article where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        Long count = queryRunner.query(sql, new ScalarHandler<Long>(),id);

        return count > 0;
    }

    //为文章点赞
    public boolean goodArticle(int id) throws SQLException {
        String sql = "update article set goodcount = goodcount + 1 where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, id);

        return count > 0;
    }

    //收藏文章
    public boolean collectArticle(int id) throws SQLException {
        String sql = "update article set collectionCount = collectionCount + 1 where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, id);

        return count > 0;
    }
}
