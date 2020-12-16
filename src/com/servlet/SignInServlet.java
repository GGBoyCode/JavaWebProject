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

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = true;
        //获取用户名
        String username = request.getParameter("username");
        //获取密码
        String password = request.getParameter("password");
        //登录时间
        Timestamp timestamp = new Timestamp(new Date().getTime());
        //创建用户实体
        User user = new User(username,password,timestamp);
        //UserServiceImpl实体
        IUserService userService = new UserServiceImpl();

        //验证用户名、密码是否正确
        try {
            //登录验证
            if(isSuccess = userService.SignIn(user)) {
                //更新最后一次登录时间
                isSuccess = userService.UpdateSignInTime(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JSONObject jsonObject = new JSONObject();
            //若为真 则登录成功
            if(isSuccess) {
                jsonObject.put("code",20000);
            } else {  //若为假 则登录失败
                jsonObject.put("code",10001);
            }
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
