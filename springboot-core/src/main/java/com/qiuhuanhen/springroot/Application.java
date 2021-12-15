package com.qiuhuanhen.springroot;


import cn.vae.singer.framework.mq.annotation.EnableRocketMq;
import cn.vae.singer.framework.mq.annotation.EnableRocketMqConsumer;
import cn.vae.singer.framework.mq.annotation.EnableRocketMqProducer;
import com.qiuhuanhen.springroot.application.command.impl.RmiCommandServiceImpl;
import com.qiuhuanhen.springroot.infrastructure.config.swagger.EnableSwagger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.impl.Log4jContextFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.qiuhuanhen.springroot.infrastructure.mapper")
@EnableSwagger
@EnableAsync
@EnableRocketMq
public class Application implements ApplicationContextInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment =
                configurableApplicationContext.getEnvironment();
        // 初始化时 启动rmi服务
        RmiCommandServiceImpl rmiCommandService = new RmiCommandServiceImpl();
        Runnable runnable = rmiCommandService.startRimServer();
        runnable.run();
        System.out.println("==init operation here =====springboot初始化完成某些操作在这里定义========");
    }
}
