package com.service.Impl;

import com.dao.IUserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.service.IUserService;

import java.sql.SQLException;

//用户Service实体类
public class UserServiceImpl implements IUserService {
    private IUserDao userDao = new UserDaoImpl();

    //账户、密码格式验证
    private boolean FormatVerify(User user){
        String regex = "1[3456789]\\d{9}";
        //账号和密码是否为空
        if(user.getUsername() == null || user.getPassword() == null){
            return false;
        }

        //密码位数是否大于6位
        if(user.getPassword().length() < 6){
            return false;
        }

        //账号是否为11位手机号
        if(!user.getUsername().matches(regex)){
            return false;
        }

        return true;
    }

    //用户信息格式验证
    private boolean UserVerify(User user){
        String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        if(user.getName() != null && user.getName().length() >= 2 ||
            user.getUserEmail() != null && user.getUserEmail().matches(regex) ||
            user.getUserType() >= 0 && user.getUserType() <= 2 ||
            user.getSex() >= 0 && user.getSex() <= 1
        ) {
           return true;
        }

        return false;
    }

    //用户登录验证
    public boolean SignIn(User user) throws SQLException{
        if(FormatVerify(user)){
            return userDao.SignIn(user);
        } else {
            return false;
        }
    }

    //用户注册
    public boolean SignUp(User user) throws SQLException{
        if(FormatVerify(user) && !userDao.SelectByUsername(user.getUsername())){
            return userDao.SignUp(user);
        } else {
            return false;
        }
    }

    //更新用户登录时间
    public boolean UpdateSignInTime(User user) throws SQLException{
        if(user != null){
            return userDao.UpdateSignInTime(user);
        } else {
          return false;
        }
    }

    //获取用户信息
    public User getUserInformation(String username) throws SQLException {
        if(username == null) {
            return null;
        } else if(!userDao.SelectByUsername(username)) {
            return null;
        } else {
            return userDao.getUserInformation(username);
        }
    }

    //更新用户信息
    public boolean UpdateUserInformation(User user) throws SQLException {
        if(userDao.SelectByUsername(user.getUsername()) && UserVerify(user)) {
            return userDao.UpdateUserInformation(user);
        } else {
            return false;
        }
    }

    //修改用户密码
    public boolean UpdatePassword(User user) throws SQLException {
        if(userDao.SelectByUsername(user.getUsername()) && user.getPassword() != null) {
            return userDao.UpdatePassword(user);
        } else {
            return false;
        }
    }

    //修改用户头像
    public boolean UpdateUserPicture(User user) throws SQLException {
        if(userDao.SelectByUsername(user.getUsername()) && user.getUserPicture()!= null) {
            return userDao.UpdateUserPicture(user);
        } else {
            return false;
        }
    }
}
