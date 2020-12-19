package com.dao.impl;

import com.dao.IWareDao;
import com.entity.Ware;
import com.util.DBCP;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class WareDaoImpl implements IWareDao {
    //获取商品信息
    public List<Ware>  getWares() throws SQLException {
        String sql = "select * from ware";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        return queryRunner.query(sql, new BeanListHandler<>(Ware.class));
    }

    //获取单个商品信息
    public Ware getWareById(int id) throws SQLException {
        String sql = "select * from ware where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        return queryRunner.query(sql, new BeanHandler<>(Ware.class),id);
    }

    //查询商品是否存在
    public boolean existId(int id) throws SQLException {
        String sql = "select count(*) from ware where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        Long count = queryRunner.query(sql, new ScalarHandler<Long>(),id);

        return count > 0;
    }

    //添加商品信息
    public boolean addWare(Ware ware) throws SQLException {
        String sql = "insert into ware(name, url, price, description, type) values(?, ?,?, ?, ?)";
        Object[] params = new Object[]{ware.getName(), ware.getUrl(), ware.getPrice(), ware.getDescription(), ware.getType()};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, params);

        return count > 0;
    }

    //修改商品信息
    public boolean updateWareInformation(Ware ware) throws SQLException {
        String sql  = "update ware set name = ?, price = ?, description = ? where id = ?";
        Object[] params = new Object[]{ware.getName(), ware.getPrice(), ware.getDescription(), ware.getId()};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, params);

        return count > 0;
    }
}
