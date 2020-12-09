package com.dao;

import com.entity.Carousel;

import java.sql.SQLException;
import java.util.List;

//轮播图Dao接口
public interface ICarouselDao {
    //添加轮播图
    public boolean addCarousel(Carousel carousel) throws SQLException;
    //删除轮播图
    public boolean deleteCarousel(int id) throws SQLException;
    //替换轮播图
    public boolean updateCarousel(Carousel carousel) throws SQLException;
    //id是否存在
    public boolean existId(int id) throws SQLException;
    //通过id获取轮播图相对地址
    public Carousel getCarouselUrl(int id) throws SQLException;
    //获取全部轮播图相对地址
    public List<String> getAllCarouselUrl() throws SQLException;
}
