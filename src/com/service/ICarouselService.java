package com.service;

import com.entity.Carousel;

import java.sql.SQLException;
import java.util.List;

public interface ICarouselService {
    //添加轮播图
    public boolean addCarousel(Carousel carousel) throws SQLException;
    //删除轮播图
    public boolean deleteCarousel(int id) throws SQLException;
    //替换轮播图
    public boolean updateCarousel(Carousel carousel) throws SQLException;
    //获取轮播图相对地址
    public String getCarouselUrl(int id) throws SQLException;
    //获取全部轮播图相对地址
    public List<String> getAllCarouselUrl() throws SQLException;
}
