package com.jw.business.common.service;

import com.jw.business.common.modal.EmailBean;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description:
 * @param: ${params}
 * @return: ${returns}
 */
public interface CommonService {
    //发送邮件
    void sendEmail(EmailBean emailBean);
}
