package com.service;

import com.entity.Ware;

import java.sql.SQLException;
import java.util.List;

public interface IShoppingCartService {
    //添加商品进入购物车
    public boolean addWare(String userId, int wareId) throws SQLException;
    //从购物车中删除商品
    public boolean deleteWare(String userId, int wareId) throws SQLException;
    //获取用户购物车商品信息
    public List<Ware> getWareByUserId(String userId) throws SQLException;
}
