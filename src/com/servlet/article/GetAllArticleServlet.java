package com.servlet.article;

import com.alibaba.fastjson.JSONObject;
import com.entity.Article;
import com.service.IArticleService;
import com.service.Impl.ArticleServiceImpl;
import com.util.DBCP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/article/all")
public class GetAllArticleServlet extends HttpServlet {
    private IArticleService articleService = new ArticleServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isSuccess = true;

        List<Article> list = null;
        try {
            list = articleService.getAllArticle();
            if(list == null) isSuccess = false;
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
            jsonObject.put("data", list);
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
