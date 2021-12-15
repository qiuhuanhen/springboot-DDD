package com.qiuhuanhen.springroot.infrastructure.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
public class AsyncInBootConfig {

    /** 核心线程池大小 */
    private int corePoolSize = 20;

    /**
     * 最大可创建的线程数
     */
    private int maxPoolSize = 50;

    /**
     * 队列最大长度
     */
    private int queueCapacity = 100;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int keepAliveSeconds = 300;


    @Bean(name = "threadPool")
    /**
     * springboot 线程池方式
     */
    public ThreadPoolTaskExecutor threadPoolTaskExecutor()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }






    /**
     * Description: java线程池方式 阿里规定显式创建 定义线程数 防止线程过多OOM
     * date: 2021/10/15
     * @author qiuKaiJian
     * @param nThreads
     */
    public  ExecutorService newFixedThreadPool(int nThreads) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("myPool").build();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), namedThreadFactory);


        return threadPoolExecutor;
    }

}
