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

    //用户登录验证
    public boolean SignIn(User user) throws Exception{
        if(FormatVerify(user)){
            return userDao.SignIn(user);
        } else {
            return false;
        }
    }

    //用户注册
    public boolean SignUp(User user) throws Exception{
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
}
