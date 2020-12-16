package com.servlet;

import com.alibaba.fastjson.JSONObject;
import com.entity.User;
import com.service.IUserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名
        String username = request.getParameter("username");
        //获取密码
        String password = request.getParameter("password");
        //注册时间
        Timestamp signUpTime = new Timestamp(new Date().getTime());
        //登录时间
        Timestamp signInTime = signUpTime;
        //创建用户实体
        User user = new User(username, password, username, 0, 0, signInTime, signUpTime);
        //创建UserServiceImpl实体
        IUserService userService = new UserServiceImpl();
        boolean isSuccess = false;

        try {
            //注册
            isSuccess = userService.SignUp(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JSONObject json = new JSONObject();
            //成功 则返回20000
            if(isSuccess){
                json.put("code",20000);
            } else { //失败 则返回10001
                json.put("code",10001);
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
