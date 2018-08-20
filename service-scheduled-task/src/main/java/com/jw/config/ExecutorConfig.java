package com.jw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description: 创建一个配置类ExecutorConfig，用来定义如何创建一个ThreadPoolTaskExecutor
 * @param: ${params}
 * @return: ${returns}
 */

@Configuration
@EnableAsync
public class ExecutorConfig {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Autowired
    private TaskThreadPoolConfig config;

    @Bean
    public Executor asyncServiceExecutor() {
        logger.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setThreadNamePrefix("async-service-");
        //配置核心线程数
        executor.setCorePoolSize(config.getCorePoolSize());
        //配置最大线程数
        executor.setMaxPoolSize(config.getMaxPoolSize());
        //配置队列大小
        executor.setQueueCapacity(config.getQueueCapacity());
        //线程内容执行完后停止时间
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("MyExecutor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}