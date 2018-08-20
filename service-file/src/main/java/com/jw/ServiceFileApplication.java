package com.jw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableEurekaClient
public class ServiceFileApplication extends SpringBootServletInitializer {

    @Value("${file.maxFileSize}")
    private String maxFileSize;
    @Value("${file.maxRequestSize}")
    private String maxRequestSize;


	public static void main(String[] args) {
		SpringApplication.run(ServiceFileApplication.class, args);
	}

    /*为了打包springboot项目*/
    @Override
    protected SpringApplicationBuilder configure(
        SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件大小限制
        factory.setMaxFileSize(maxFileSize); //KB,MB
        // 单次上传文件大小限制
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }
}
