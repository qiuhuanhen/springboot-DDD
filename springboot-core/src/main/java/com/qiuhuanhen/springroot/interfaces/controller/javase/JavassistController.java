package com.qiuhuanhen.springroot.interfaces.controller.javase;

import javassist.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Description: 动态字节码技术
 * date: 2021/10/18
 * @author qiuKaiJian

 */
@RestController
@RequestMapping("/javassist")
public class JavassistController {

    @GetMapping
    public void test() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        // 创建一个空类
        CtClass ctClass = pool.makeClass("com.qiuhuanhen.application.bo.JavassistBO");

//        // 获取类
//        CtClass ctClass = pool.getCtClass("com.qiuhuanhen.application.bo.JavassistBO");

        // 创建字段
        CtField ctField = new CtField(pool.get("java.lang.String"),"newField",ctClass);

        // 设置字段访问权限
        ctField.setModifiers(Modifier.PRIVATE);

        // 设置字段初始值
        ctClass.addField(ctField,CtField.Initializer.constant("xxx"));

        // 生成get set 方法
        ctClass.addMethod(CtNewMethod.setter("setNewField",ctField));
        ctClass.addMethod(CtNewMethod.getter("getNewField",ctField));

        // 添加无参构造
        CtConstructor constructor = new CtConstructor(new CtClass[]{},ctClass);
        ctClass.addConstructor(constructor);

        // 添加有参构造
        CtConstructor constructor1 = new CtConstructor(new CtClass[]{pool.get("java.lang.String")}, ctClass);
        ctClass.addConstructor(constructor1);

        // 创建一个test方法 无返回值 无参数
        CtMethod test = new CtMethod(CtClass.voidType, "test", new CtClass[]{}, ctClass);
        test.setModifiers(Modifier.PUBLIC);
        // 设置方法体
        test.setBody("{System.out.println(\"-------\");}");
        ctClass.addMethod(test);

        ctClass.setModifiers(Modifier.PUBLIC);
        // 生成class  路径为项目class路径
        ctClass.writeFile("D:\\Springboot\\target\\classes");



    }

}
