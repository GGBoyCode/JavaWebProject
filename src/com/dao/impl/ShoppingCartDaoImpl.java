package com.dao.impl;

import com.dao.IShoppingCartDao;
import com.entity.Ware;
import com.util.DBCP;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ShoppingCartDaoImpl implements IShoppingCartDao {
    //添加商品进入购物车
    public boolean addWare(String userId, int wareId) throws SQLException {
        String sql = "insert into shopping_cart(userid, waresid) values(?, ?)";
        Object[] params = new Object[]{userId, wareId};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, params);

        return count > 0;
    }

    //从购物车删除商品
    public boolean deleteWare(String userId, int wareId) throws SQLException {
        String sql = "delete from shopping_cart where userid = ? and waresid = ?";
        Object[] params = new Object[]{userId, wareId};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, params);

        return count > 0;
    }

    //获取用户购物车信息
    public List<Ware> getWareByUserId(String userId) throws SQLException {
        String sql = "select * from ware where ware.id in (select waresid from shopping_cart where userid = ?)";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        return queryRunner.query(sql, new BeanListHandler<>(Ware.class), userId);
    }
}
