package com.servlet.comment;

import com.alibaba.fastjson.JSONObject;
import com.entity.Comment;
import com.entity.User;
import com.service.ICommentService;
import com.service.IUserService;
import com.service.Impl.CommentServiceImpl;
import com.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/comment/get")
public class GetCommentServlet extends HttpServlet {
    private ICommentService commentService = new CommentServiceImpl();
    private IUserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        boolean isSuccess = true;

        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        List<Comment> list = null;
        JSONObject[] objs = null;
        try {
            list = commentService.getComment(articleId, page, limit);
            int n = list.size();
            objs = new JSONObject[n];
            if(list != null) {
                for(int i = 0;i < n;i++) {
                    User user = userService.getUserInformation(list.get(i).getUserId());
                    objs[i] = JSONObject.parseObject(JSONObject.toJSONString(list.get(i)));
                    objs[i].put("user", user);
                }
            } else {
                isSuccess = false;
            }
        } catch (SQLException e) {
            isSuccess = false;
            e.printStackTrace();
        } finally {
            JSONObject jsonObject = new JSONObject();
            if(isSuccess) {
                jsonObject.put("code", 20000);
            } else {
                jsonObject.put("code", 10000);
            }
            jsonObject.put("data", objs);
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
