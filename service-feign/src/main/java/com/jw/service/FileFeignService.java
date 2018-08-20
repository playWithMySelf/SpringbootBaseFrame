package com.jw.service;

import com.jw.base.FeignSpringFormEncoder;
import feign.Param;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description:
 * @param: ${params}
 * @return: ${returns}
 */
@FeignClient(name = "service-file",configuration = FileFeignService.MultipartSupportConfig.class)
public interface FileFeignService{

    /**
     *@author: jinwei【jin_wei@founder.com.cn】
     *@description: 后台使用feign负载请求eureka中service-file服务
     * param使用LinkedMultiValueMap类型才能支持value为json字符串或表单字符串
     *@create: 2018/8/7 18:45
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map upload(@RequestPart("file") MultipartFile[] file,@RequestParam("fileType") String fileType,@RequestParam("belongToSystem") String belongToSystem);


     /**
      *@author: jinwei【jin_wei@founder.com.cn】
      *@description: 多文件上传表单编码配置
      *@create: 2018/8/20 19:04
      */
    @Configuration
    public class MultipartSupportConfig {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        @Primary
        @Scope("prototype")
        public Encoder feignFormEncoder() {
            return new FeignSpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }

}
