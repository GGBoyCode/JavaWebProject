package com.servlet.carousel;

import com.alibaba.fastjson.JSONObject;
import com.entity.Carousel;
import com.service.ICarouselService;
import com.service.Impl.CarouselServiceImpl;
import com.util.CarouselUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/carousel/delete")
public class DeleteCarouselServlet extends HttpServlet {
    private ICarouselService carouselService= new CarouselServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isSuccess = true;
        try {
            //获取图片相对位置
            String url = carouselService.getCarouselUrl(id);
            if(url != null) {
                //获取图片绝对位置
                String path = getServletContext().getRealPath("/") + url.replaceAll("/", "\\\\");
                //将图片从服务器中删除
                if(!carouselService.deleteCarousel(id) || !CarouselUtil.delete(path)) isSuccess = false;
            } else {
                isSuccess = false;
            }
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
            out.print(jsonObject);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
