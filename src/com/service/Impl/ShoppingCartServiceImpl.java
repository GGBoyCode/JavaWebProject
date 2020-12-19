package com.service.Impl;

import com.dao.IShoppingCartDao;
import com.dao.IUserDao;
import com.dao.IWareDao;
import com.dao.impl.ShoppingCartDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.dao.impl.WareDaoImpl;
import com.entity.Ware;
import com.service.IShoppingCartService;

import java.sql.SQLException;
import java.util.List;

public class ShoppingCartServiceImpl implements IShoppingCartService {
    private IWareDao wareDao = new WareDaoImpl();
    private IUserDao userDao = new UserDaoImpl();
    private IShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
    //将商品添加进入购物车
    public boolean addWare(String userId, int wareId) throws SQLException {
        if(wareDao.existId(wareId) && userDao.SelectByUsername(userId)) {
            return shoppingCartDao.addWare(userId, wareId);
        } else {
            return false;
        }
    }

    //从购物车中删除商品
    public boolean deleteWare(String userId, int wareId) throws SQLException {
        if(wareDao.existId(wareId) && userDao.SelectByUsername(userId)) {
            return shoppingCartDao.deleteWare(userId, wareId);
        } else {
            return false;
        }
    }

    //获取用户购物车商品信息
    public List<Ware> getWareByUserId(String userId) throws SQLException {
        if(userDao.SelectByUsername(userId)) {
            return shoppingCartDao.getWareByUserId(userId);
        } else {
            return null;
        }
    }
}
