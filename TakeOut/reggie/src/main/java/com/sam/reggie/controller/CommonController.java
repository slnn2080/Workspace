package com.sam.reggie.controller;

import com.sam.reggie.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

  @Value("${reggie.path}")
  private String basePath;

  @PostMapping("/upload")
  // 文件上传表单项的name名为file 所以我们的形参名为file
  public Result<String> upload(MultipartFile file) {

    // 创建一个目录对象
    File dir = new File(basePath);
    // 如果该目录不存在则创建该目录
    if(!dir.exists()) {
      // 创建该目录
      dir.mkdirs();
    }

    // 获取原始的文件名(获取上传文件的文件名)
    String originalFilename = file.getOriginalFilename();

    // 获取文件名后缀
    String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

    // 获取新的文件名
    String fileName = UUID.randomUUID().toString() + suffix;

      // 将临时文件转存到指定的位置
    try {
      file.transferTo(new File(basePath + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 文件上传的返回值最好的 filename 因为前端需要上传后的文件名, 因为dish表中的image字段存储的就是上传后的文件名
    return Result.success(fileName);
  }


  // 文件下载:
  // 通过输出流向浏览器页面写数据就可以了 不需要返回值
  @GetMapping("/download")
  // name: 接收url上的name参数 要下载的文件名
  public void download(String name, HttpServletResponse res) {
    // 通过输入流: 根据文件名 将文件读取到内存中
    try {
      FileInputStream fis = new FileInputStream(new File(basePath + name));

      // 通过输出流: 将文件写回浏览器 在浏览器展示图片
      ServletOutputStream outputStream = res.getOutputStream();

      // 设置响应回去的文件类型
      res.setContentType("image/jpeg");

      byte[] bytes = new byte[1024];
      int len = 0;
      while((len = fis.read(bytes)) != -1) {
        outputStream.write(bytes, 0, len);
        outputStream.flush();
      }

      // 关闭资源
      outputStream.close();
      fis.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
