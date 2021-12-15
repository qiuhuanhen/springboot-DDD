package com.qiuhuanhen.springroot.interfaces.controller.springbase;

import com.qiuhuanhen.springroot.interfaces.controller.aop.AopController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * 构造推断
 */
@Controller
public class ConstructorController {

    @Autowired
    ConstructorController constructorController;
    public ConstructorController() {
        System.out.println("----Default---");
    }

    @Autowired
    public ConstructorController(AopController aopController) {
        System.out.println("---auto-----");
    }

    @PostConstruct
    public void  test() {
        System.out.println(constructorController);
    }
}
