package com.servlet.ware;

import com.alibaba.fastjson.JSONObject;
import com.entity.Ware;
import com.service.IWareService;
import com.service.Impl.WareServiceImpl;
import com.util.WareUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ware/add")
public class AddWareServlet extends HttpServlet {
    private IWareService wareService = new WareServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        boolean isSuccess = true;
        try {
            //获取商品实例
            Ware ware = WareUtil.upload(request);
            if(ware != null) {
                ware.setType(0);
                isSuccess = wareService.addWare(ware);
            } else {
                isSuccess = false;
            }
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
