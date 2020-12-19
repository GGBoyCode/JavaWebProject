package com.servlet.shoppingCart;

import com.alibaba.fastjson.JSONObject;
import com.service.Impl.ShoppingCartServiceImpl;
import com.service.IShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/shoppingCart/delete")
public class DeleteWareServlet extends HttpServlet {
    private IShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = true;
        String userId = request.getParameter("userId");
        int wareId = Integer.parseInt(request.getParameter("wareId"));

        try {
            shoppingCartService.deleteWare(userId, wareId);
        } catch (SQLException e) {
            isSuccess = true;
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
