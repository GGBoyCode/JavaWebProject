package com.util;

import com.entity.Carousel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

//轮播图帮助类
public class CarouselUtil {
    //上传配置
    private static final String MEMORY_PATH = "repository";    //缓存区path
    private static final int MEMORY_SIZE = 1024*1024;  //缓存区大小 1MB
    private static final String UPLOAD_PATH = "upload";    //上传地址
    private static final int MAX_UPLOAD_SIZE = 1024*1024*50;   //单个文件上传大小 50MB
    private static final int MAX_REQUEST_SIZE = 1024*1024*200; //总文件上传大小 200MB

    //上传图片
    public static Carousel upload(HttpServletRequest request) throws Exception {
        Carousel carousel = new Carousel();
        //检查是否为上传项
        if(ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置缓存区大小 单位（byte）
            factory.setSizeThreshold(MEMORY_SIZE);
            //设置缓存区域位置
            //缓存区域路径(相对当前应用目录)
            String memoryPath = request.getServletContext().getRealPath("/") + File.separator + MEMORY_PATH;
            File repDir = new File(memoryPath);
            if(!repDir.exists()) repDir.mkdir();
            //如果文件不存在则创建
            factory.setRepository(repDir);

            //创建ServletFileUpload对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            //设置单个文件最大上传值
            upload.setFileSizeMax(MAX_UPLOAD_SIZE);
            //设置总文件最大上传值
            upload.setSizeMax(MAX_REQUEST_SIZE);

            //上传文件路径(相对当前应用目录)
            String uploadPath = request.getServletContext().getRealPath("/") + File.separator + UPLOAD_PATH;
            File uploadDir = new File(uploadPath);
            //如果文件不存在则创建
            if(!uploadDir.exists()) uploadDir.mkdir();

            //解析request
            List<FileItem> fileItems = upload.parseRequest(request);
            //若fileItems不为空且大小不为0 则进行下一步处理
            if(fileItems != null && fileItems.size() > 0) {
                //遍历fileItems数组
                for(FileItem item:fileItems) {
                    //判断是否为普通字段 若不为普通字段则为文件字段
                    if(!item.isFormField()) {
                        //获取文件名称
                        //item.getName()可能得到“路径”+“文件名”通过new File()
                        //转换为后再通过getName()可以得到真正的文件名
                        String filename = new File(item.getName()).getName();
                        int pointIndex = filename.lastIndexOf(".");
                        //获取filename后缀名
                        String suffix = filename.substring(pointIndex + 1);
                        //重命名 在后面加8个随机字符串防止名字重复
                        filename = filename.substring(0, pointIndex) + RandomStringUtils.randomAlphanumeric(8) + "." + suffix;
                        //设置轮播图路径
                        carousel.setUrl(UPLOAD_PATH + "/" + filename);
                        //检验上传是否为图片
                        if(suffix.matches("jpg|jpeg|png|")) {
                            //文件位置
                            String filepath = uploadPath + File.separator + filename;
                            File file = new File(filepath);
                            //将文件写入服务器硬盘
                            item.write(file);
                        } else {
                            return null;
                        }
                    } else {
                        if("id".equals(item.getFieldName())) {
                            //获取id
                            carousel.setId(Integer.parseInt(item.getString()));
                        } else {
                            return null;
                        }
                    }
                }
            }
        }

        return carousel;
    }

    //删除图片
    public static boolean delete(String path) {
        File file = new File(path);
        //将图片从服务器中删除
        return file.delete();
    }
}
