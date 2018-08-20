package com.jw;

import com.jw.config.TaskThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
//根据配置文件的内容去创建线程池Bean
@EnableConfigurationProperties({TaskThreadPoolConfig.class})
public class ServiceScheduledTaskApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ServiceScheduledTaskApplication.class, args);
	}

    //为了打包springboot项目
    @Override
    protected SpringApplicationBuilder configure(
        SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
