package com.jw.web;

import com.jw.base.BaseAction;
import com.jw.service.FileFeignService;
import com.jw.service.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description:
 * @param: ${params}
 * @return: ${returns}
 */
@RestController
public class FeignFileController extends BaseAction {
    @Autowired
    FileFeignService fileFeignService;

    @RequestMapping(value = "/upload")
    public Map getUser(@RequestPart("file") MultipartFile[] file, HttpServletRequest req){
        LinkedMultiValueMap param = this.getParameterMap(req);
        Map a = fileFeignService.upload(file,"image","test");
        return a;
    }
}
