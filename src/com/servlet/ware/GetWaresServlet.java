package com.servlet.ware;

import com.alibaba.fastjson.JSONObject;
import com.entity.Ware;
import com.service.IWareService;
import com.service.impl.WareServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ware")
public class GetWaresServlet extends HttpServlet {
    private IWareService wareService = new WareServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = true;
        List<Ware> list = null;
        try {
            //获取商品数据
            list =  wareService.getWares();
        } catch (SQLException e) {
            isSuccess = false;
            list = null;
            e.printStackTrace();
        } catch (Exception e) {
            isSuccess = false;
            list = null;
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
