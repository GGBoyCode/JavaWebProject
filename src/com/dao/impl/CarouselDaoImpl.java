package com.dao.impl;

import com.dao.ICarouselDao;
import com.entity.Carousel;
import com.util.DBCP;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;
import java.sql.SQLException;

//轮播图实现类
public class CarouselDaoImpl implements ICarouselDao {
    //添加轮播图
    public boolean addCarousel(Carousel carousel) throws SQLException {
        String sql = "insert into carousel(id, url) values(?, ?)";
        Object[] params = new Object[]{carousel.getId(), carousel.getUrl()};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, params);

        return count > 0;
    }

    //删除轮播图
    public boolean deleteCarousel(int id) throws SQLException {
        String sql = "delete from carousel where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, id);

        return count > 0;
    }

    //更新轮播图
    public boolean updateCarousel(Carousel carousel) throws SQLException {
        String sql = "update carousel set url = ? where id = ?";
        Object[] params = new Object[]{carousel.getUrl(), carousel.getId()};

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        int count = queryRunner.update(sql, params);

        return count > 0;
    }

    //id是否存在
    public boolean existId(int id) throws SQLException {
        String sql = "select count(1) from carousel where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        Long count = queryRunner.query(sql, new ScalarHandler<Long>(), id);

        return count > 0;
    }

    //获得轮播图相对位置
    public Carousel getCarouselUrl(int id) throws SQLException {
        String sql = "select * from carousel where id = ?";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        return queryRunner.query(sql, new BeanHandler<Carousel>(Carousel.class),id);
    }

    //获取全部轮播图相对地址
    public List<String> getAllCarouselUrl() throws SQLException {
        String sql = "select url from carousel";

        QueryRunner queryRunner = new QueryRunner(DBCP.dataSource);
        return queryRunner.query(sql, new ColumnListHandler<String>());
    }
}
