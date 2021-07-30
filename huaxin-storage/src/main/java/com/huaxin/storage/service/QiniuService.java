package com.huaxin.storage.service;

import com.qiniu.common.QiniuException;

import java.io.File;
import java.io.InputStream;

public interface QiniuService {

    /**
     * 以文件的形式上传
     *
     * @param file
     * @param fileName:
     * @return: java.lang.String
     */
    String uploadFile(File file, String fileName) throws QiniuException;

    /**
     * 以流的形式上传
     *
     * @param inputStream
     * @param fileName:
     * @return: java.lang.String
     */
    String uploadFile(InputStream inputStream, String fileName) throws QiniuException;

    /**
     * 删除文件
     *
     * @param fileName:
     * @return: java.lang.String
     */
    String delete(String fileName) throws QiniuException;

}

