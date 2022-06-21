package com.gametrader.gametraderuserservice;

import com.gametrader.gametraderuserservice.util.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableEurekaClient
public class GametraderUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GametraderUserServiceApplication.class, args);
    }

}
