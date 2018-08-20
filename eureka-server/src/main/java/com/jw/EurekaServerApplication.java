package com.jw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**通过注解@EnableEurekaServer 表明自己是一个eurekaserver**/
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

    //为了打包springboot项目
    @Override
    protected SpringApplicationBuilder configure(
        SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
