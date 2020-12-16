package com.servlet.carousel;

import com.alibaba.fastjson.JSONObject;
import com.entity.Carousel;
import com.service.ICarouselService;
import com.service.impl.CarouselServiceImpl;
import com.util.CarouselUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;

@WebServlet("/carousel/add")
public class AddCarouselServlet extends HttpServlet {
    private ICarouselService carouselService = new CarouselServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //是否成功
        boolean isSuccess = true;

        try {
            //获取上传图片信息
            Carousel carousel = CarouselUtil.upload(request);
            if(carousel != null) {
                //添加信息至数据库
                isSuccess = carouselService.addCarousel(carousel);
            }
        } catch (SQLException e){
            isSuccess = false;
            e.printStackTrace();
        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
        } finally {
            JSONObject jsonObject = new JSONObject();
            PrintWriter out = response.getWriter();
            if(isSuccess) {
                jsonObject.put("code", 20000);
            } else {
                jsonObject.put("code", 10000);
            }
            out.print(jsonObject);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
