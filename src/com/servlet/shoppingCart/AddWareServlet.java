package com.servlet.shoppingCart;

import com.alibaba.fastjson.JSONObject;
import com.service.IShoppingCartService;
import com.service.Impl.ShoppingCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/shoppingCart/add")
public class AddWareServlet extends HttpServlet {
    private IShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = false;

        String userId = request.getParameter("userId");
        int wareId = Integer.parseInt(request.getParameter("wareId"));

        try {
            isSuccess = shoppingCartService.addWare(userId, wareId);
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

            PrintWriter out = response.getWriter();
            out.print(jsonObject);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
