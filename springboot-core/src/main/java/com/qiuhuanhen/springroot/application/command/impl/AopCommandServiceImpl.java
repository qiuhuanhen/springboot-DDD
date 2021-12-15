package com.qiuhuanhen.springroot.application.command.impl;


import com.qiuhuanhen.springroot.application.command.AopCommandService;
import com.qiuhuanhen.springroot.application.command.TestCommandService;
import com.qiuhuanhen.springroot.domain.model.OperLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;
/**
 * Description: （被）aop增删改业务层  注：在controller切 该业务是被切方法<br/>
 * date: 2021/12/6 20:32<br/>
 * @author qkj <br/>
 * @since JDK 1.8
 */
@Service
public class AopCommandServiceImpl implements AopCommandService {
    @Autowired
    OperLogger operLogger;

    @Override

    public void testWithoutResult(String msg ) {

        System.out.println("---业务方法在执行---"+System.currentTimeMillis());
        operLogger.storeLog(UUID.randomUUID().toString());
        System.out.println("业务方法执行结束"+System.currentTimeMillis());
    }

}
