package com.dao;

import com.entity.User;

import java.sql.SQLException;

//用户Dao接口
public interface IUserDao {
    //用户登录
    public boolean SignIn(User user) throws Exception;
    //用户注册
    public boolean SignUp(User user) throws Exception;
    //根据username查询用户
    public boolean SelectByUsername(String username) throws Exception;
    //更新用户登录时间
    public boolean UpdateSignInTime(User user) throws SQLException;
}
