package org.csu.mypetstore.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.csu.mypetstore.api.persistence")
public class MypetstoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MypetstoreApiApplication.class, args);
    }

}
