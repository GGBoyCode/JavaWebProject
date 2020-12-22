package com.dao;

import com.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface ICommentDao {
    //添加评论
    public boolean addComment(Comment comment) throws SQLException;
    //删除评论
    public boolean deleteComment(int id) throws SQLException;
    //获取评论
    public List<Comment> getComment(int articleId) throws SQLException;
    //点赞评论
    public boolean goodComment(int id) throws SQLException;
    //评论是否存在
    public boolean existComment(int id) throws SQLException;
}
