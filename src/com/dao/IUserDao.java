package com.dao;

import com.entity.User;

import java.sql.SQLException;

//用户Dao接口
public interface IUserDao {
    //用户登录
    public boolean SignIn(User user) throws SQLException;
    //用户注册
    public boolean SignUp(User user) throws SQLException;
    //根据username查询用户
    public boolean SelectByUsername(String username) throws SQLException;
    //更新用户登录时间
    public boolean UpdateSignInTime(User user) throws SQLException;
    //查询用户信息
    public User getUserInformation(String username) throws SQLException;
    //更新用户信息
    public boolean UpdateUserInformation(User user) throws SQLException;
    //修改用户密码
    public boolean UpdatePassword(User user) throws SQLException;
    //修改用户头像
    public boolean UpdateUserPicture(User user) throws SQLException;
}
