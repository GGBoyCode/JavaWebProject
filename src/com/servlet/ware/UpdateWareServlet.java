package com.servlet.ware;

import com.alibaba.fastjson.JSONObject;
import com.entity.Ware;
import com.service.IWareService;
import com.service.Impl.WareServiceImpl;
import sun.text.resources.cldr.lag.FormatData_lag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/ware/update")
public class UpdateWareServlet extends HttpServlet {
    private IWareService wareService = new WareServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = false;

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        String description = request.getParameter("description");
        Ware ware = new Ware(id, name, null, price, description);

        try {
            isSuccess = wareService.updateWareInformation(ware);
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
