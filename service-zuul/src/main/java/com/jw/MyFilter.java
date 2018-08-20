package com.jw;

import com.jw.utils.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by jw on 2017/4/8.
 */
@Component
public class MyFilter extends ZuulFilter{

    //redis工具类
    //@Resource(name="redisUtil")
    //protected RedisUtil redisUtil;

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        testRedis();
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        }
        log.info("ok");
        return null;
    }

    public void testRedis(){
        /*if(redisUtil.hasKey("admin")){
            log.warn("redis读取: "+redisUtil.get("admin"));
        }else{
            log.warn("redis插入: "+redisUtil.set("admin","admin-123456"));
            redisUtil.expire("admin",180L);//超时时间180秒
        }*/
    }
}
