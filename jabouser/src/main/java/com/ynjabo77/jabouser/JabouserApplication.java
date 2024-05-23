package com.ynjabo77.jabouser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.ynjabo77.jabouser.mapper")
public class JabouserApplication {

    public static void main(String[] args) {
        SpringApplication.run(JabouserApplication.class, args);
    }

}
