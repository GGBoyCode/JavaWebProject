package com.dao.impl;

import com.dao.ICommentDao;
import com.entity.Comment;
import com.util.DBCP;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class CommentDaoImpl implements ICommentDao {
    //添加评论
    public boolean addComment(Comment comment) throws SQLException {
        String sql = "insert into comment(userid, articleid, content, publishtime, goodcount) values (?, ?, ?, ?, ?)";
        Object[] params = new Object[]{comment.getUserId(), comment.getArticleId(), comment.getContent(), comment.getPublishTime(), comment.getGoodCount()};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, params);

        return count > 0;
    }

    //删除评论
    public boolean deleteComment(int id) throws SQLException {
        String sql = "delete from comment where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, id);

        return count > 0;
    }

    //获取评论
    public Comment getComment(int id) throws SQLException {
        String sql = "select * from comment where id = ?";

        QueryRunner  queryRunner = new QueryRunner(DBCP.dataSource);

        return queryRunner.query(sql, new BeanHandler<Comment>(Comment.class), id);
    }

    //点赞评论
    public boolean goodComment(int id) throws SQLException {
        String sql = "update comment set goodCount = goodCount + 1 where id = ?";

        QueryRunner  queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, id);

        return count > 0;
    }

    //评论是否存在
    public boolean existComment(int id) throws SQLException {
        String sql = "select count(*) from comment where id = ?";

        QueryRunner  queryRunner = new QueryRunner(DBCP.dataSource);
        Long count = queryRunner.query(sql, new ScalarHandler<Long>(), id);

        return count > 0;
    }
}
