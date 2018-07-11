package com.sawelly.fpog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sawelly.fpog.mapper")
public class FpogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FpogApplication.class, args);
    }



}

