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

@WebServlet("/ware/get")
public class GetWareByIdServlet extends HttpServlet {
    public IWareService wareService = new WareServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = true;
        int id = Integer.parseInt(request.getParameter("id"));
        Ware ware = null;

        try {
            //获取单个商品信息
            ware =  wareService.getWareById(id);
        } catch (SQLException e) {
            isSuccess = false;
            ware = null;
            e.printStackTrace();
        } catch (Exception e) {
            isSuccess = false;
            ware = null;
            e.printStackTrace();
        } finally {
            JSONObject jsonObject = new JSONObject();
            if(isSuccess) {
                jsonObject.put("code", 20000);
            } else {
                jsonObject.put("code", 10000);
            }
            jsonObject.put("date", ware);
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
