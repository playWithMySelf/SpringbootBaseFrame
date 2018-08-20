package com.jw.business.common.service.impl;

import com.jw.business.common.modal.EmailBean;
import com.jw.business.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description: 系统通用工具方法，调用多线程进行处理
 * @param: ${params}
 * @return: ${returns}
 */
@Service
public class CommonServiceImpl implements CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

     /**
      *@author: jinwei【jin_wei@founder.com.cn】
      *@description: 利用线程池发送邮件
      *@create: 2018/8/4 15:08
      */
    @Override
    @Async("asyncServiceExecutor")
    public void sendEmail(EmailBean emailBean) {
        logger.info("*****开始发送邮件****");
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("*****结束发送邮件****");
    }
}
