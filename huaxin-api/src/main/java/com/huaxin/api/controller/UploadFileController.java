package com.huaxin.api.controller;

import com.huaxin.storage.service.QiniuService;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class UploadFileController {
    @Autowired
    private QiniuService qiniuService;

    /**
     * 上传文件
     * @param multipartFile
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    public String uploadTest(@RequestParam("file") MultipartFile multipartFile,
                             HttpServletRequest request) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        String path = "../img";
        // 获取文件名称
        String filename = multipartFile.getOriginalFilename();
        // 将文件上传的服务器根目录下的upload文件夹
        File file = new File(path, filename);
        return qiniuService.uploadFile(inputStream,filename);
    }

    /**
     * 根据文件名删除文件
     * @param fileName
     * @return
     * @throws QiniuException
     */
    @RequestMapping("/deleteImg")
    public String deleteImg(String fileName) throws QiniuException {
        return qiniuService.delete(fileName);
    }
}

