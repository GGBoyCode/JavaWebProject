package com.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.entity.User;
import com.service.IUserService;
import com.service.impl.UserServiceImpl;
import com.util.UserUtil;
import com.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/user/update/avatar")
public class UpdateUserPictureServlet extends HttpServlet {
    private IUserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = true;
        try {
            //获取用户信息
            User user = userService.getUserInformation("18180443372");
            //若用户有头像 则删除头像
            if(user.getUserPicture() != null) {
                //获取图片绝对位置
                String path = getServletContext().getRealPath("/") + user.getUserPicture().replaceAll("/", "\\\\");
                isSuccess = Util.delete(path);
            }

            if(isSuccess) {
                //图片相对地址
                String path = UserUtil.upload(request);
                if(path != null) {
                    user.setUserPicture(path);
                    isSuccess = userService.UpdateUserPicture(user);
                } else {
                    isSuccess = false;
                }
            }
        } catch (SQLException e) {
            isSuccess = false;
            e.printStackTrace();
        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
        } finally {
            JSONObject jsonObject = new JSONObject();
            //成功则返回20000 否则返回10000
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
