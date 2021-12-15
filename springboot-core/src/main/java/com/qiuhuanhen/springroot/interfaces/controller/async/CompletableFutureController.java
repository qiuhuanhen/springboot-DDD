package com.qiuhuanhen.springroot.interfaces.controller.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description: CompletableFuture 实现异步<br/>
 * 尽管jdk8有了这种骚操作 为了项目方便维护 推荐统一交给一个线程池管理
 * date: 2021/12/6 22:58<br/>
 *
 * @author qkj <br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/future")
public class CompletableFutureController {
    @GetMapping
    public String test() {
        long start = System.currentTimeMillis();
        // 异步实现 底层启了一个线程
        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("11111");
            return 1;
        });

        CompletableFuture<Integer> sec = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("22222");
            return 2;
        });


        // 等待所有异步线程都完成 输出结果为aaa,222,111,bbb,1,2
        CompletableFuture.allOf(first, sec);
        System.out.println("-----aaa-------");
        try {
            Integer integer = first.get();
            System.out.println("-------bbb-----");
            Integer integer1 = sec.get();
            System.out.println(integer);
            System.out.println(integer1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - start);
        return String.valueOf(System.currentTimeMillis() - start);
    }
}
