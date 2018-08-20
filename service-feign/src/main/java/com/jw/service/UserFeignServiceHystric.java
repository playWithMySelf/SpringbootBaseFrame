package com.jw.service;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description:
 * @param: ${params}
 * @return: ${returns}
 */
@Component
public class UserFeignServiceHystric implements UserFeignService{


    @Override
    public Map dispatch(LinkedMultiValueMap map) {
        String result = "sorry,the service is unnormal. ";
        Map resmap = new HashMap();
        resmap.put("msg",result);
        return resmap;
    }
}
