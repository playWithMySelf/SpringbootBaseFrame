package com.jw.web;

import com.jw.base.BaseAction;
import com.jw.service.UserFeignService;
import feign.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by jw on 2017/4/6.
 */
@RestController
public class FeignGetuserController extends BaseAction{

    @Autowired
    UserFeignService userFeignService;

    @RequestMapping(value = "/getUser")
    public Map getUser(HttpServletRequest req, HttpServletResponse res){
        LinkedMultiValueMap param = this.getParameterMap(req);
        Map a = userFeignService.dispatch(param);
        return a;
    }

}
