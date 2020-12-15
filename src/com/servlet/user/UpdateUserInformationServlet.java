package com.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.entity.User;
import com.service.IUserService;
import com.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/user/information/update")
public class UpdateUserInformationServlet extends HttpServlet {
    private IUserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = true;
        //获取用户修改信息
        String name = request.getParameter("name");
        int sex = Integer.parseInt(request.getParameter("sex"));
        int userType = Integer.parseInt(request.getParameter("userType"));
        String userEmail = request.getParameter("userEmail");
        String userAddress = request.getParameter("userAddress");

        //实例化用户类
        User user = new User("18180443372", name, sex, userEmail, userAddress, userType);
        try {
            isSuccess = userService.UpdateUserInformation(user);
        } catch (SQLException e) {
            isSuccess = false;
            e.printStackTrace();
        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
        } finally {
            JSONObject jsonObject = new JSONObject();

            //成功返回20000 否则返回10000
            if(isSuccess) {
                jsonObject.put("code", 20000);
            } else {
                jsonObject.put("code", 10000);
            }
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
