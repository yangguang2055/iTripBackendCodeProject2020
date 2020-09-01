package com.whackode.itrip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@MapperScan({"com.whackode.itrip.dao","com.whackode.itrip.util"})
@EnableEurekaClient
@SpringBootApplication
public class ItripBizProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItripBizProviderApplication.class, args);
	}

}
