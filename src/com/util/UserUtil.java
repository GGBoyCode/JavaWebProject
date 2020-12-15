package com.util;

import com.entity.Carousel;
import com.entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class UserUtil {
    //文件夹名称
    private static final String DIRNAME = "avatar";

    //上传图片
    public static String upload(HttpServletRequest request) throws Exception {
        String path = null;
        //检查是否为上传项
        if(ServletFileUpload.isMultipartContent(request)) {
            List<FileItem> fileItems = Util.getFileItems(request, DIRNAME);
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
                        path = Util.UPLOAD_PATH + "/" + DIRNAME + "/" + filename;
                        //检验上传是否为图片
                        if(suffix.matches("jpg|jpeg|png|")) {
                            //文件位置
                            String filepath = Util.Path + DIRNAME + File.separator + filename;
                            File file = new File(filepath);
                            //将文件写入服务器硬盘
                            item.write(file);
                        } else {
                            return null;
                        }
                    }
                }
            }
        }

        return path;
    }
}
