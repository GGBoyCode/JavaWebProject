package com.service;

import com.entity.Ware;

import java.sql.SQLException;
import java.util.List;
//商品业务逻辑层
public interface IWareService {
    //获取商品信息
    public List<Ware> getWares() throws SQLException;
    //获取单个商品信息
    public Ware getWareById(int id) throws SQLException;
    //添加商品信息
    public boolean addWare(Ware ware) throws SQLException;
    //修改商品信息
    public boolean updateWareInformation(Ware ware) throws SQLException;
}
