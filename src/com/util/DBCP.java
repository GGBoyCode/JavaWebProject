package com.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DBCP {
    public static DataSource dataSource;

    static{
        try{
            //创建DataSource
            dataSource = createDataSource();
        } catch (Exception e){
            //打印错误
            e.printStackTrace();
        }
    }

    public static DataSource createDataSource() throws Exception{
        //读取properties文件
        Properties properties = new Properties();
        //将dbcpInfo.properties文件转为输入流
        InputStream inputStream = new DBCP().getClass().getClassLoader().getResourceAsStream("dbcpInfo.properties");
        properties.load(inputStream);

        //根据properties文件创建DataSource
        return BasicDataSourceFactory.createDataSource(properties);
    }
}
