package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.example.mapper")
@SpringBootApplication
public class TecsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecsApplication.class, args);
    }

}
