package com.servlet.carousel;

import com.alibaba.fastjson.JSONObject;
import com.entity.Carousel;
import com.service.ICarouselService;
import com.service.impl.CarouselServiceImpl;
import com.util.CarouselUtil;
import com.util.Util;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/carousel/update")
public class UpdateCarouselServlet extends HttpServlet {
    private ICarouselService carouselService = new CarouselServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = true;
        try {
            //上传图片
            //获取上传图片信息
            Carousel carousel = CarouselUtil.upload(request);

            //获取图片相对位置
            String url = carouselService.getCarouselUrl(carousel.getId());
            if(url != null) {
                //获取图片绝对位置
                String path = getServletContext().getRealPath("/") + url.replaceAll("/", "\\\\");
                //将图片从服务器中删除
                if(!carouselService.deleteCarousel(carousel.getId()) || !Util.delete(path)) isSuccess = false;
            }

            if(carousel != null) {
                //添加信息至数据库
                isSuccess = carouselService.addCarousel(carousel);
            }
        } catch (FileUploadException e) {
            isSuccess = false;
            e.printStackTrace();
        } catch (SQLException e) {
            isSuccess = false;
            e.printStackTrace();
        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
        } finally {
            JSONObject jsonObject = new JSONObject();
            if(isSuccess) {
                jsonObject.put("code", 20000);
            } else {
                jsonObject.put("code", 10000);
            }
            PrintWriter out = response.getWriter();
            out.println(jsonObject);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
