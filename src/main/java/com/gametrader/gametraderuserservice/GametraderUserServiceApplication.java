package com.gametrader.gametraderuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GametraderUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GametraderUserServiceApplication.class, args);
	}

}
