package com.finance.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient


public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
		//new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

}
