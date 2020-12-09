package com.entity;

import java.sql.Timestamp;

//用户实体类
public class User {
    private String username;    //账号
    private String password;    //密码
    private String name;    //用户昵称
    private String userPicture; //用户头像
    private String userAddress;  //用户地址
    private int userType;   //用户类型
    private Timestamp signUpTime;   //用户注册时间
    private Timestamp signInTime;   //用户最后一次登录时间

    public User(String username,String password,Timestamp signInTime){
        this(username,password,signInTime,null);
    }

    public User(String username,String password,Timestamp signInTime,Timestamp signUpTime){
        this.username = username;
        this.password = password;
        this.signInTime = signInTime;
        this.signUpTime = signUpTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Timestamp getSignUpTime() {return signUpTime;}

    public void setSignUpTime(Timestamp signUpTime) {this.signUpTime = signUpTime;}

    public Timestamp getSignInTime() {return signInTime;}

    public void setSignInTime(Timestamp signInTime) {this.signInTime = signInTime;}
}
