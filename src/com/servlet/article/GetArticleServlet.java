package com.servlet.article;

import com.alibaba.fastjson.JSONObject;
import com.entity.Article;
import com.service.IArticleService;
import com.service.Impl.ArticleServiceImpl;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = true;
        int id = Integer.parseInt(request.getParameter("id"));
        Article article = null;
        try {
            article = articleService.getArticle(id);
            if(article == null) isSuccess = false;
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
            jsonObject.put("data", article);

            PrintWriter out = response.getWriter();
            out.print(jsonObject);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
