package com.example.lostandfound.controller;

import com.example.lostandfound.entity.Images;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * Description:
 *
 * @date:2023/4/12 9:36
 * @author: ilpvc
 */
@RestController
@CrossOrigin
@RequestMapping("/lostandfound/upload")
@Slf4j
public class UploadFileController {

    @Autowired
    Images images;

    @PostMapping("/image")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        // 处理上传的文件，例如保存到磁盘或数据库等
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String outputPath = "D:/static/image/";
                String fileName = UUID.randomUUID().toString()+ "."+Objects.requireNonNull(file.getContentType()).split("/")[1];
                String outputFile = outputPath + fileName;
                OutputStream outputStream = new FileOutputStream(outputFile);
                outputStream.write(bytes, 0, bytes.length);
                outputStream.close();
                images.getImages().add(outputFile);
                log.info(file.getContentType());
                // TODO: 处理文件
                return "http://localhost:8080/static/image/"+fileName;
            } catch (IOException e) {
                // TODO: 处理异常
                return "上传失败：" + e.getMessage();
            }
        } else {
            return "请选择要上传的文件！";
        }
    }
}
