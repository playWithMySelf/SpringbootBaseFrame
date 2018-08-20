package com.jw.scheduledtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description: 定时器统一存放类
 * @param: ${params}
 * @return: ${returns}
 */
@Component
@Configurable
@EnableScheduling
@EnableAsync
public class ScheduledTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    @Async("asyncServiceExecutor")
    @Scheduled(cron = "30 * *  * * ? ")
    public void startSchedule() {
        logger.info("*****多线程定时器1(5s/次)运行中*****");
    }

    @Async("asyncServiceExecutor")
    @Scheduled(cron = "30 * *  * * ? ")
    public void startSchedule2() {
        logger.info("*****多线程定时器2(5s/次)运行中*****");
    }
}
