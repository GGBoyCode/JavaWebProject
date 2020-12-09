package com.servlet.carousel;

import com.alibaba.fastjson.JSONObject;
import com.service.ICarouselService;
import com.service.Impl.CarouselServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carousel/get")
public class GetCarouselServlet extends HttpServlet {
    private ICarouselService carouselService= new CarouselServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = true;
        List<String> list = new ArrayList<String>();
        try {
            list = carouselService.getAllCarouselUrl();
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
