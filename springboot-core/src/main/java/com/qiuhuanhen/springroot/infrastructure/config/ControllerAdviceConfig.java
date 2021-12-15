package com.qiuhuanhen.springroot.infrastructure.config;

import org.springframework.web.bind.annotation.*;

/**
* Description: RestControllerAdvice的作用<br/>
* date: 2021/12/6 23:01<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
@RestControllerAdvice
public class ControllerAdviceConfig {

    /***
     *  advice+ExceptionHandler =  全局捕获异常
     */
    @ExceptionHandler(NullPointerException.class)
    public String handlerException() {
        return "空指针异常";

    }

    @ModelAttribute
    /***
     *  advice + ModelAttribute = 全局数据预绑定
     *  可以理解为存数据在model里面
     *  （@ModelAttribute(name = )可以取不同名字 写多个方法 存多个model）
     */
    public String preModel() {
        String model = "xxxx";
        return model;
    }


    // advice + InitBinder = 全局数据预处理

}
