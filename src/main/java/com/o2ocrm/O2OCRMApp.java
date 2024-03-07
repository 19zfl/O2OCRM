package com.o2ocrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName:O2OCRMApp
 * @Description: 程序启动类
 * @Author:zfl19
 * @CreateDate:2024/3/6 23:33
 */

@SpringBootApplication
@MapperScan("com.o2ocrm.*.mapper")
public class O2OCRMApp {
    public static void main(String[] args) {
        SpringApplication.run(O2OCRMApp.class,  args);
    }
}
