package com.aster.bcu.printroom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
//@ComponentScan(basePackages = {"com.aster.bcu.printroom.mapper",
//        "com.aster.bcu.printroom.controller",
//        "com.aster.bcu.printroom.service",
//        "com.aster.bcu.printroom.service.impl",
//        "com.aster.bcu.printroom.entity","com.aster.bcu.printroom.config"})
@MapperScan(value = "com.aster.bcu.printroom.mapper")
public class PrintroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrintroomApplication.class, args);
    }

}
