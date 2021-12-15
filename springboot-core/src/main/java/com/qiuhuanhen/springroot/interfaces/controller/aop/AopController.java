package com.qiuhuanhen.springroot.interfaces.controller.aop;


import com.qiuhuanhen.springroot.application.command.AopCommandService;
import com.qiuhuanhen.springroot.infrastructure.annotation.aop.AopAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 切面应用示例  使用 threadLocal保障线程安全  <br/>
 *
 * @author qkj <br/>
 * @see AopAnno
 * date: 2021/12/6 22:46<br/>
 * @since JDK 1.8
 */


@RestController
@RequestMapping("/test")
public class AopController {
    @Autowired
    private AopCommandService aopService;

    @GetMapping

    @AopAnno
    public String test(@RequestParam("msg") String msg) {
        aopService.testWithoutResult(msg);
        System.out.println("controller结束" + System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName());
        // 返回时间 可以直观看出实现了异步
        return "return:" + System.currentTimeMillis();
    }
}
