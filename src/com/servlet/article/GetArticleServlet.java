package com.servlet.article;

import com.alibaba.fastjson.JSONObject;
import com.entity.Article;
import com.entity.User;
import com.service.IArticleService;
import com.service.IUserService;
import com.service.Impl.ArticleServiceImpl;
import com.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/article/get")
public class GetArticleServlet extends HttpServlet {
    private IArticleService articleService = new ArticleServiceImpl();
    private IUserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        boolean isSuccess = true;
        int id = Integer.parseInt(request.getParameter("id"));
        Article article = null;
        User user = null;
        JSONObject obj = null;
        try {
            article = articleService.getArticle(id);
            user = userService.getUserInformation(article.getUserId());
            if(user != null && article != null) {
                obj = JSONObject.parseObject(JSONObject.toJSONString(article));
                obj.put("user", user);
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

            jsonObject.put("data", obj);

            PrintWriter out = response.getWriter();
            out.print(jsonObject);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
