package com.leevan.controller;

import cn.hutool.core.io.FileTypeUtil;
import com.leevan.mapper.FileMapper;
import com.leevan.pojo.Files;
import com.leevan.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;
import org.springframework.util.StringUtils;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.controller
 * @className:      fileController
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/14 17:10
 * @version:    1.0
 */ 
@RestController
@RequestMapping("/file")
public class fileController {

    @Value("${files.upload.path}")
    private String fileUpLoadPath;

    @Autowired
    private FileMapper fileMapper;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        Integer size = (int) (file.getSize()/1024);
        String originalFilename = file.getOriginalFilename();
        String type = StringUtils.getFilenameExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();
        String name = uuid + "." + type;
        String url = "http://localhost:8080/file/down/" + name;
//        判断文件上传目录是否存在
        File uploadParentFile = new File(fileUpLoadPath);
        if (!uploadParentFile.exists()) {
//            判断配置的文件目录是否存在,若不存在则创建一个新的文件目录
            uploadParentFile.mkdirs();
        }
//        String uuid= UUID.randomUUID().toString();
        File file1 = new File(fileUpLoadPath + uuid+"."+type);
        file.transferTo(file1);
        Files files = new Files(originalFilename, type, size, url);
        fileMapper.insert(files);
//        存储到数据库
        return name;
    }

    @RequestMapping("/down/{fileName}")
    public Result download(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        File file = new File(fileUpLoadPath + fileName);
        if (!file.exists()) {
            return Result.fail("文件不存在");
        }
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buff = new byte[1024];
            ServletOutputStream os = response.getOutputStream();
            int i = 0;
            while ((i = bufferedInputStream.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Result.fail("文件下载失败");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("下载失败");
        }
        return Result.ok("下载成功");
    }
}