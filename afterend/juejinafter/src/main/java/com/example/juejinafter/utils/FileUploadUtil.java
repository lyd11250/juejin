package com.example.juejinafter.utils;


import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUploadUtil {
    /**
     * 上传文件
     *
     * @param multipartFile
     * @return 文件存储路径
     */
    public static String upload(MultipartFile multipartFile) {
        // 文件存储位置，文件的目录要存在才行，可以先创建文件目录，然后进行存储

        String filePath = getUploadPath() +"/"+ multipartFile.getOriginalFilename();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文件存储
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
    /**
     * 获取当前系统路径
     */
    //TODO 待理解
    public static String getUploadPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()){path = new File("");}
        File upload = new File(path.getAbsolutePath(), "static/upload");
        if (!upload.exists()) {upload.mkdirs();}
        return upload.getAbsolutePath();
    }




}
