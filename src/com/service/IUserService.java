package com.service;

import com.entity.User;

import java.sql.SQLException;

//用户Service接口
public interface IUserService {
    //用户登录验证
    public boolean SignIn(User user) throws SQLException;
    //用户注册
    public boolean SignUp(User user) throws SQLException;
    //更新用户登录时间
    public boolean UpdateSignInTime(User user) throws SQLException;
    //获取用户信息
    public User getUserInformation(String username) throws SQLException;
    //更新用户信息
    public boolean UpdateUserInformation(User user) throws SQLException;
    //修改用户密码
    public boolean UpdatePassword(User user) throws SQLException;
    //修改用户头像
    public boolean UpdateUserPicture(User user) throws SQLException;
}
