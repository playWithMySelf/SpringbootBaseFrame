package com.jw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 *@author: jinwei【jin_wei@founder.com.cn】
 *@description: 使用springboot内置tomcat进行部署的话，在编写websocket具体实现类之前，要注入ServerEndpointExporter，
 *   这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint。而如果使用war包部署到tomcat中进行部署的话，
 *   就不必做此步骤，因为它将由容器自己提供和管理
 *@create: 2018/7/18 20:41
 */
@Configuration
public class WebSocketConfig {
   @Bean
   public ServerEndpointExporter serverEndpointExporter(){
       return new ServerEndpointExporter();
   }
}