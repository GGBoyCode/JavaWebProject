package com.service;

import com.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface ICommentService {
    //添加评论
    public boolean addComment(Comment comment) throws SQLException;
    //删除评论
    public boolean deleteComment(int id) throws SQLException;
    //获取评论
    public List<Comment> getComment(int articleId, int page, int limit) throws SQLException;
    //获取评论数
    public int getCommentCount(int articleId) throws SQLException;
    //点赞评论
    public boolean goodComment(int id) throws SQLException;
}
