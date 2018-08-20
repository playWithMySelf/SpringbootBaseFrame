package com.jw.base;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.util.ApplicationContextProvider;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: jinwei
 * @create: 2018-04-11 15:05
 * @description: Spring分发请求调度控制器
 */

@SuppressWarnings("unchecked")
@Controller
@Scope("prototype")
@CrossOrigin
public class GatewayController extends BaseAction{

    //log4j日志
    Logger logger = Logger.getLogger(GatewayController.class.getName());

    @RequestMapping("/gateway")
    @ResponseBody
    public Map dispatch(HttpServletRequest req){
        //页面参数
        Map<String, Object> param  = this.getParameterMap2(req);
        String service = (String) param.get("service");
        String method = (String) param.get("method");
        String paramtype = (String) param.get("paramtype");
        //获取service和method
        Map dataMap = new HashMap();
        dataMap.put("code","EC99");
        if(service == null || service.length()<=0){
            logger.debug("****GatewayController中获取service失败***");
            dataMap.put("mag","请输入正确的service");
            return dataMap;
        }
        if(method == null || method.length()<=0){
            logger.debug("****GatewayController中获取method失败***");
            dataMap.put("mag","请输入正确的method");
            return dataMap;
        }
        //获取参数类型，默认的是Map。目前只支持一个参数。如Map、User
        Class paramClass = Map.class;
        try {
            if(StringUtils.isNotBlank(paramtype)){
                paramClass = Class.forName(paramtype);
            }
        }catch (Exception e){
            logger.debug("****GatewayController中获取参数类型失败***");
            dataMap.put("mag","请输入正确的paramtype");
            return dataMap;
        }

        try{
            Method  mh = ReflectionUtils.findMethod(ApplicationContextProvider.getBean(service).getClass(), method,paramClass);
            Object result = null;
            if(StringUtils.isNotBlank(paramtype)){
                //json字符串转成bean
                String formInfo = (String) param.get("formInfo");
                ObjectMapper mapper = new ObjectMapper();
                //当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
                //因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                result = ReflectionUtils.invokeMethod(mh,  ApplicationContextProvider.getBean(service),mapper.readValue(formInfo,paramClass));
            }else{
                result = ReflectionUtils.invokeMethod(mh,  ApplicationContextProvider.getBean(service),param);
            }
            dataMap = (HashMap)result;
        }catch(Exception e){
            e.printStackTrace();
            logger.error("GatewayController调用服务和方法失败!");
            dataMap.put("msg","服务分发失败，请检查参数是否正确!");
            return dataMap;
        }

        return dataMap;
    }


}
