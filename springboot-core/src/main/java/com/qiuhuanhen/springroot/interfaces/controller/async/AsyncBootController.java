package com.qiuhuanhen.springroot.interfaces.controller.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: springboot里面使用线程池
 * <p>
 * 配合config里面的 AsyncInBootConfig <br/>
 * @see com.qiuhuanhen.springroot.infrastructure.config.AsyncInBootConfig
 * date: 2021/10/20 23:05<br/>
 *
 * @author qkj <br/>
 * @since JDK 1.8
 */

@RestController
@RequestMapping("/async/boot")
public class AsyncBootController {

    /**
     * @Lazy解决循环依赖的问题
     */

    @Autowired
    @Lazy
    private AsyncBootController asyncBootController;

    /**
     * 需要在application 开启异步
     */
    @Async("threadPool")
    public void test() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("test----------------");
    }


    @Async("threadPool")
    public void test2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("test2------------------");
    }

    @Autowired
    /** 如果全局只有一个线程池bean 可以不用声明名字 **/
    @Qualifier("threadPool")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @GetMapping
    public void starter() {
        long l = System.currentTimeMillis();
        // 代理对象去调用
        asyncBootController.test();
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l + "-------test-------");
        asyncBootController.test2();
        long l3 = System.currentTimeMillis();
        System.out.println(l3 - l2 + "---------test2--------");
        System.out.println(System.currentTimeMillis() - l + "---all---");


        threadPoolTaskExecutor.execute(() -> {
            System.out.println("----threadPoolTaskExecutor---");
        });
    }

}
