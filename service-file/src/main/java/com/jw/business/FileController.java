package com.jw.business;

import com.jw.utils.DateUtil;
import com.jw.utils.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: jinwei
 * @description: 文件上传下载微服务
 */
@Controller
@Scope("prototype")
public class FileController extends BaseAction{

    @Value("${file.baseUrl}")
    private String baseUrl;

     /**
      *@author: jinwei【jin_wei@founder.com.cn】
      *@description: 根据文件类型获取本地存储的路径
      *@create: 2018/8/14 13:29
      */
    private String getUrlByType(String type,String system){
        String url = "";
        switch (type){
            case "image" :
                url += "images/";
                break;
            case "video" :
                url += "videos/";
                break;
            case "doc" :
                url += "docs/";
                break;
            default:
                url += "others/";
                break;
        }
        String currentDay = DateUtil.getDateFormat(new Date(), "yyyyMMdd");
        url += system+"/"+currentDay;
        return url;
    }

     /**
      *@author: jinwei【jin_wei@founder.com.cn】
      *@description: 文件上传，可支持多类型
      * file : 支持多文件同时上传
      * fileType : 文件类型
      * belongToSystem : 文件属于的系统英文名称
      *@create: 2018/8/14 13:29
      */
    @RequestMapping("/upload")
    @ResponseBody
    public Map upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request){

        /* 页面参数 */
        Map<String, Object> param  = this.getParameterMap2(request);
        /* 文件类型 */
        String fileType = (String) param.get("fileType");
        /* 文件属于的系统英文名称 */
        String belongToSystem = (String) param.get("belongToSystem");

        /*返回值*/
        String url="";
        Map resMap = new HashMap();
        resMap.put("code","EC00");
        resMap.put("msg","上传成功");
        resMap.put("url",url);

        /*参数检测*/
        if(!StringUtils.isNotBlank(fileType)){
            resMap.put("code","EC01");
            resMap.put("msg","请填写文件类型!");
            resMap.put("url",url);
            return resMap;
        }
        if(!StringUtils.isNotBlank(belongToSystem)){
            resMap.put("code","EC01");
            resMap.put("msg","请填写文件所属系统英文名称!");
            resMap.put("url",url);
            return resMap;
        }
        if(file.length <1){
            resMap.put("code","EC01");
            resMap.put("msg","未检测到文件,请至少选择一个文件!");
            resMap.put("url",url);
            return resMap;
        }

        //获取文件 存储位置
        String storageUrl = getUrlByType(fileType,belongToSystem);

        File pathFile = null;
        try {
            pathFile = new File(baseUrl+storageUrl);
            if (!pathFile.exists()) {
                //文件夹不存 创建文件
                pathFile.mkdirs();
            }
            for (MultipartFile f : file) {

                System.out.println("文件类型："+f.getContentType());
                System.out.println("文件名称："+f.getOriginalFilename());
                System.out.println("文件大小:"+f.getSize());
                System.out.println(".................................................");

                //生成随机文件名称
                String subfixName = UUIDUtil.generateShortUuid()+f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));

                //将文件copy上传到服务器
                f.transferTo(new File(baseUrl+storageUrl+"/"+subfixName));
                logger.info("文件上传描述:上传至"+baseUrl+storageUrl+"/"+subfixName);
                url += ","+storageUrl+"/"+subfixName;
            }
            //返回存放文件路径
            if(StringUtils.isNotBlank(url)){
                url = url.substring(1);
                resMap.put("url",url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("code","EC02");
            resMap.put("msg","文件上传失败："+e.getMessage());
            resMap.put("url",url);
            return resMap;
        }

        return resMap;
    }


    /**
     *@author: jinwei【jin_wei@founder.com.cn】
     *@description: 下载
     *@create: 2018/3/7 21:49
     */
    @RequestMapping("/download")
    public ModelAndView download(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        //页面参数
        Map<String, Object> param  = this.getParameterMap2(request);

        String storeName= (String) param.get("filePath");
        String contentType = "application/octet-stream";
        try {
            download(request, response, storeName, contentType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    //文件下载 主要方法
    public  void download(HttpServletRequest request,
                                HttpServletResponse response, String storeName, String contentType
    ) throws Exception {

        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        //获取下载文件路径
        String downLoadPath = baseUrl+"/"+ storeName;

        //获取文件的长度
        long fileLength = new File(downLoadPath).length();

        //设置文件输出类型
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename="
            + new String(storeName.getBytes("utf-8"), "ISO8859-1"));
        //设置输出长度
        response.setHeader("Content-Length", String.valueOf(fileLength));
        //获取输入流
        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        //输出流
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        //关闭流
        bis.close();
        bos.close();
    }
}
