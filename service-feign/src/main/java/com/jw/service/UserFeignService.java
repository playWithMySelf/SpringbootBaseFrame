package com.jw.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by jw on 2018/8/6.
 * value：注册中心中服务的id
 * fallback：断路器调用方法
 */
@FeignClient(value = "service-user",fallback = UserFeignServiceHystric.class)
public interface UserFeignService {

     /**
      *@author: jinwei【jin_wei@founder.com.cn】
      *@description: 后台使用feign负载请求eureka中service-user服务
      * param使用LinkedMultiValueMap类型才能支持value为json字符串或表单字符串
      *@create: 2018/8/7 18:45
      */
    @RequestMapping(value = "/gateway", method = RequestMethod.POST)
    Map dispatch(LinkedMultiValueMap map);

}
