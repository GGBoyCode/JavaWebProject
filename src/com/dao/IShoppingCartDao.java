package com.dao;

import com.entity.Ware;

import java.sql.SQLException;
import java.util.List;

public interface IShoppingCartDao {
    //将商品添加进入购物车
    public boolean addWare(String userId, int wareId) throws SQLException;
    //将商品从购物车删除
    public boolean deleteWare(String userId, int wareId) throws SQLException;
    //获取用户购物车商品信息
    public List<Ware> getWareByUserId(String userId) throws SQLException;
}
