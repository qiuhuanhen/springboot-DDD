package com.qiuhuanhen.springroot.interfaces.controller.async;


import com.qiuhuanhen.springroot.application.command.TestCommandService;
import com.qiuhuanhen.springroot.infrastructure.DO.TestT;
import com.qiuhuanhen.springroot.infrastructure.mapper.TTestTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java里面的线程池简单测试
 * 注意 ： 本类中创建线程池方式仅为demo或小型项目可使用 阿里规范不推荐
 * 正确示例也有写 {@link com.qiuhuanhen.springroot.infrastructure.config.AsyncInBootConfig#newFixedThreadPool}
 * @author qkj
 */
@RestController
@RequestMapping("/pool")
public class ThreadPoolController implements Runnable  {

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    @Autowired
    private TestCommandService testCommandServiceImpl;
    @Autowired
    private TTestTMapper testtMapper;
    int count = 1;

    @GetMapping

    public void test() {
          executorService.execute(this);
    }

    @Override
    public synchronized void run() {
        TestT enty = new TestT();
        enty.setDes(UUID.randomUUID().toString());
        enty.setMark(count);
        testCommandServiceImpl.save(enty);
        int insert = testtMapper.insert(enty);
        System.out.println(insert);
        count++;
    }
}
