package com.dao.impl;

import com.dao.IUserDao;
import com.entity.User;
import com.util.DBCP;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

//用户Dao实体类
public class UserDaoImpl implements IUserDao {
    //用户登录查询
    public boolean SignIn(User user) throws SQLException{
        String sql = "select count(1) from user where username = ? and password = ?";
        Object[] params = new Object[]{user.getUsername(),user.getPassword()};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        Long count = queryRunner.query(sql, new ScalarHandler<Long>(),params);

        return count > 0;
    }

    //用户注册
    public boolean SignUp(User user) throws SQLException{
        String sql = "insert into user(username, password, sex, usertype, signuptime, signintime) values(?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]{user.getUsername(), user.getPassword(), user.getSex(), user.getUserType(),user.getSignUpTime(), user.getSignInTime()};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql,params);

        return count > 0;
    }

    //根据username查询用户
    public boolean SelectByUsername(String username) throws SQLException{
        String sql = "select count(1) from user where username = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        Long count = queryRunner.query(sql,new ScalarHandler<Long>(),username);

        return count > 0;
    }

    //更新用户登录时间
    public boolean UpdateSignInTime(User user) throws SQLException {
        String sql = "update user set signintime = ? where username = ?";
        Object params = new Object[]{user.getSignInTime(),user.getUsername()};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql,params);

        return count > 0;
    }

    //查询用户信息
    public User getUserInformation(String username) throws SQLException {
        String sql = "select * from user where username = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        return queryRunner.query(sql, new BeanHandler<User>(User.class), username);
    }
}
