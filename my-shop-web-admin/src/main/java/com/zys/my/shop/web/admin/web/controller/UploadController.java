package com.zys.my.shop.web.admin.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @program: my-shop
 * @description: 上传控制器
 * @author: Leo
 * @create: 2019-06-12 17:29
 **/
@Controller
public class UploadController {

    private static final String UPLOAD_PATH = "/static/upload/";

    /**
    * @Description: 文件上传
    * @Param: [dropFile, editorFile] Dropzone上传, wangEditor上传
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Leo
    */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile,MultipartFile editorFile, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        //前端上传的文件
        MultipartFile myFile = dropFile == null ? editorFile : dropFile;
        //文件名
        String fileName = myFile.getOriginalFilename();
        //后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        //文件上传路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        //判断路径是否存在
        File file = new File(filePath);
        //不存在 则创建
        if (!file.exists()){
            file.mkdir();
        }
        //将文件更名后写入指定路径下
        file = new File(filePath, UUID.randomUUID() + fileSuffix);
        try {
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Dropzone上传
        if (dropFile != null){
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            result.put("fileName",serverPath + UPLOAD_PATH + file.getName());
        }
        //wangEditor上传
        else{
            /*
             * request.getScheme()：服务端提供的协议名称 http/https
             * request.getServerName()：服务器名称 localhost/ip/domain
             * request.getServerPort()：服务器端口号 8080 ...
             * */
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            result.put("errno",0);
            result.put("data",new String[]{ serverPath + UPLOAD_PATH + file.getName()});
        }
        return result;
    }
}