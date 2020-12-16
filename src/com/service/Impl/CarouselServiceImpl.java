package com.service.impl;

import com.dao.ICarouselDao;
import com.dao.impl.CarouselDaoImpl;
import com.entity.Carousel;
import com.service.ICarouselService;

import java.sql.SQLException;
import java.util.List;

public class CarouselServiceImpl implements ICarouselService {
    private ICarouselDao carouselDao = new CarouselDaoImpl();
    //添加轮播图
    public boolean addCarousel(Carousel carousel) throws SQLException {
        if(carousel.getUrl() == null) {
            return false;
        } else if(carouselDao.existId(carousel.getId())) {
            return false;
        } else {
            return carouselDao.addCarousel(carousel);
        }
    }

    //删除轮播图
    public boolean deleteCarousel(int id) throws SQLException {
        if(!carouselDao.existId(id)) {
            return false;
        } else {
            return carouselDao.deleteCarousel(id);
        }
    }

    //替换轮播图
    public boolean updateCarousel(Carousel carousel) throws SQLException{
        if(!carouselDao.existId(carousel.getId())) {
            return false;
        } else {
            return carouselDao.updateCarousel(carousel);
        }
    }

    //获取轮播图相对地址
    public String getCarouselUrl(int id) throws SQLException {
        if(!carouselDao.existId(id)) {
            return null;
        } else {
            return carouselDao.getCarouselUrl(id).getUrl();
        }
    }

    //获取全部轮播图相对地址
    public List<String> getAllCarouselUrl() throws SQLException {
        return carouselDao.getAllCarouselUrl();
    }
}
