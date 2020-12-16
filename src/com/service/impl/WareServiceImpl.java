package com.service.impl;

import com.dao.IWareDao;
import com.dao.impl.WareDaoImpl;
import com.entity.Ware;
import com.service.IWareService;

import java.sql.SQLException;
import java.util.List;

public class WareServiceImpl implements IWareService {
    private IWareDao wareDao = new WareDaoImpl();

    //获取商品信息
    public List<Ware> getWares() throws SQLException {
        return wareDao.getWares();
    }

    //获取单个商品信息
    public Ware getWareById(int id) throws SQLException {
        if(wareDao.existId(id)) {
            return wareDao.getWareById(id);
        } else {
            return null;
        }
    }

    //添加商品信息
    public boolean addWare(Ware ware) throws SQLException {
        if(ware.getName() != null && ware.getUrl() != null && ware.getDescription() != null && ware.getPrice() > 0) {
            return wareDao.addWare(ware);
        } else {
            return false;
        }
    }

    //修改商品信息
    public boolean updateWareInformation(Ware ware) throws SQLException {
        if(ware.getName() != null && ware.getPrice() > 0 && ware.getDescription() != null) {
            return wareDao.updateWareInformation(ware);
        } else {
            return false;
        }
    }
}
