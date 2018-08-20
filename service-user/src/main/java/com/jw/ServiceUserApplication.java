package com.jw;

import com.jw.config.TaskThreadPoolConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 *@author: jinwei【jin_wei@founder.com.cn】
 *@description:
 *  1、SpringBootApplication ：springboot 入口标注
 *  2、MapperScan：将项目中对应的mapper类的路径加进来就可以了；多个用","隔开；也可以改成"com.*.mapper"
 *  3、EnableEurekaClient : 表明自己是一个eurekaclient
 *  4、ServletComponentScan : 必须得加这个，不然filter无效
 *  5、ComponentScan ： 注解扫描包
 *@create: 2018/6/6 17:05
 */
@SpringBootApplication
@MapperScan("com.jw.business.*.mapper")
@EnableEurekaClient
@ServletComponentScan("com.jw.filter")
@ComponentScan(basePackages = "com")
//根据配置文件的内容去创建线程池Bean
@EnableConfigurationProperties({TaskThreadPoolConfig.class})
public class ServiceUserApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ApplicationContext app =SpringApplication.run(ServiceUserApplication.class, args);
    }

    //为了打包springboot项目
    @Override
    protected SpringApplicationBuilder configure(
        SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}