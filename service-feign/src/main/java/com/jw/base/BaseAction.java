package com.jw.base;

/**
 * Created by jw on 2018/8/8.
 */

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class BaseAction{


    /**
     * 获取客户端参数通用方法
     *    将前台参数装载到一个泛型Map里面
     *    考虑到传入的参数可能包括json数据或者表单数据字符串，使用LinkedMultiValueMap对象接收
     *    Spring会将其解析成application/x-www-form-urlencoded，这样就会以“键=值”的方式传递
     * @param req
     * @return  Map<String,Object>
     */
    @SuppressWarnings("unchecked")
    public LinkedMultiValueMap<String,String> getParameterMap(HttpServletRequest req){
        // 待返回的客户端参数
        LinkedMultiValueMap<String,String> clientMap = new LinkedMultiValueMap<String,String>();

        // 获取客户端请求参数Map
        Map<String,String[]> parameterMap = req.getParameterMap();

        //生成迭代器
        Iterator<String> paramIterator = parameterMap.keySet().iterator();

        //遍历客户端参数以装载至待返回的客户端参数
        while(paramIterator.hasNext()){
            String key = paramIterator.next();
            clientMap.add(key, parameterMap.get(key)[0]);
        }
        return clientMap;
    }

    /**
     * 获取客户端参数通用方法
     *  将前台参数装载到一个泛型Map里面
     *  考虑到传入的参数可能包括json数据或者表单数据字符串，使用LinkedMultiValueMap对象接收
     *  Spring会将其解析成application/x-www-form-urlencoded，这样就会以“键=值”的方式传递
     * @param req
     * @return  Map<String,Object>
     */
    public LinkedMultiValueMap<String,Object> getParameterMap2(HttpServletRequest req){
        // 待返回的客户端参数
        LinkedMultiValueMap<String,Object> clientMap = new LinkedMultiValueMap<String,Object>();

        // 获取客户端请求参数Map
        Map<String,String[]> parameterMap = req.getParameterMap();

        //生成迭代器
        Iterator<String> paramIterator = parameterMap.keySet().iterator();

        //遍历客户端参数以装载至待返回的客户端参数
        while(paramIterator.hasNext()){
            String key = paramIterator.next();
            clientMap.add(key, parameterMap.get(key)[0]);
        }
        return clientMap;
    }
}
