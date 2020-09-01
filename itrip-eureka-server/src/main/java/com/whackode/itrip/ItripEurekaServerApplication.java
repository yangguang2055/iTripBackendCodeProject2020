package com.whackode.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ItripEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItripEurekaServerApplication.class, args);
	}

}
