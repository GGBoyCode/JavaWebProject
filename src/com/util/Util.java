package com.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class Util {
    //上传配置
    private static final String MEMORY_PATH = "repository";    //缓存区path
    private static final int MEMORY_SIZE = 1024*1024;  //缓存区大小 1MB
    private static final int MAX_UPLOAD_SIZE = 1024*1024*50;   //单个文件上传大小 50MB
    private static final int MAX_REQUEST_SIZE = 1024*1024*200; //总文件上传大小 200MB
    public static final String UPLOAD_PATH = "upload";    //上传地址
    public static String Path;  //文件路径

    public static List<FileItem> getFileItems(HttpServletRequest request, String dirName) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置缓存区大小 单位（byte）
        factory.setSizeThreshold(MEMORY_SIZE);
        //设置缓存区域位置
        //缓存区域路径(相对当前应用目录)
        String memoryPath = request.getServletContext().getRealPath("/") + File.separator + MEMORY_PATH;
        File repDir = new File(memoryPath);
        if(!repDir.exists()) {
            repDir.mkdir();
        }
        //如果文件不存在则创建
        factory.setRepository(repDir);

        //创建ServletFileUpload对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置单个文件最大上传值
        upload.setFileSizeMax(MAX_UPLOAD_SIZE);
        //设置总文件最大上传值
        upload.setSizeMax(MAX_REQUEST_SIZE);

        //上传文件路径(相对当前应用目录)
        Path = request.getServletContext().getRealPath("/")+ File.separator + UPLOAD_PATH + File.separator;
        String uploadPath = Path + dirName;
        File uploadDir = new File(uploadPath);
        //如果文件不存在则创建
        if(!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        //解析request
        return upload.parseRequest(request);
    }

    //删除图片
    public static boolean delete(String path) {
        File file = new File(path);
        //将图片从服务器中删除
        return file.delete();
    }
}
