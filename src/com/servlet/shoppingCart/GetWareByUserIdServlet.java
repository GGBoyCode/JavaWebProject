package com.servlet.shoppingCart;

import com.alibaba.fastjson.JSONObject;
import com.service.Impl.ShoppingCartServiceImpl;
import com.entity.Ware;
import com.service.IShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/shoppingCart/get")
public class GetWareByUserIdServlet extends HttpServlet {
    private IShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = true;
        String userId = request.getParameter("userId");
        List<Ware> list = null;
        try {
            list = shoppingCartService.getWareByUserId(userId);
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
