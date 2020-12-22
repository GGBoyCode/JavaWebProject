package com.service.Impl;

import com.dao.IArticleDao;
import com.dao.ICommentDao;
import com.dao.IUserDao;
import com.dao.impl.ArticleDaoImpl;
import com.dao.impl.CommentDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.entity.Comment;
import com.service.ICommentService;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements ICommentService {
    private IUserDao userDao = new UserDaoImpl();
    private IArticleDao articleDao = new ArticleDaoImpl();
    private ICommentDao commentDao = new CommentDaoImpl();

    //添加评论
    public boolean addComment(Comment comment) throws SQLException {
        if(userDao.SelectByUsername(comment.getUserId()) && articleDao.existId(comment.getArticleId()) && comment.getContent() != null) {
            return commentDao.addComment(comment);
        } else {
            return false;
        }
    }

    //删除评论
    public boolean deleteComment(int id) throws SQLException {
        if(commentDao.existComment(id)) {
            return commentDao.deleteComment(id);
        } else {
            return false;
        }
    }

    //获取评论
    public List<Comment> getComment(int articleId) throws SQLException {
        if(articleDao.existId(articleId)) {
            return commentDao.getComment(articleId);
        } else {
            return null;
        }
    }
    //点赞评论
    public boolean goodComment(int id) throws SQLException {
        if(commentDao.existComment(id)) {
            return commentDao.goodComment(id);
        } else {
            return false;
        }
    }
}
