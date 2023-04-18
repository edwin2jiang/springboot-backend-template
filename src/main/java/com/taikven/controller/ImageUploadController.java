package com.taikven.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taikven.entity.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2023/4/12
 * @Version: 1.0
 */

@RestController
@RequestMapping("/image")
public class ImageUploadController {
    // 定义允许上传的图片类型
    private final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png");

    @PostMapping("upload")
    public Result uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        // 检查上传的文件是否为空
        if (file.isEmpty()) {
            return Result.fail("上传的文件不能为空");
        }

        // 检查上传的文件类型是否为允许的类型
        String contentType = file.getContentType();
        if (!ALLOWED_CONTENT_TYPES.contains(contentType)) {
            return Result.fail("上传的文件类型不允许");
        }

        // 检查上传的文件大小是否超过限制（此处限制为10MB）
        if (file.getSize() > 10 * 1024 * 1024) {
            return Result.fail("上传的文件过大，不能超过10MB");
        }

        // 保存上传的文件
        String fileName = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        Path path = Paths.get("uploads/" + fileName);

        // 如果父文件夹不存在，则创建
        if (!path.getParent().toFile().exists()) {
            path.getParent().toFile().mkdirs();
        }

        Files.write(path, bytes);

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        json.put("url", "/image/preview/" + fileName);

        // 返回成功的响应
        return Result.success(json,"上传成功");
    }


    @GetMapping("preview/{fileName:.+}")
    public ResponseEntity<byte[]> previewImage(@PathVariable String fileName) throws IOException {
        // 加载上传的图片
        Path path = Paths.get("uploads/" + fileName);
        byte[] bytes = Files.readAllBytes(path);

        // 构造响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(bytes.length);

        // 返回响应
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}


