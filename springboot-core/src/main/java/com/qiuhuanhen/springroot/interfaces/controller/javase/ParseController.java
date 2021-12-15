package com.qiuhuanhen.springroot.interfaces.controller.javase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qiuhuanhen.springroot.application.bo.PeopleBO;

/**
 * ClassName: ParseController <br/>
 * Description: java原生方式实现字符串转实体类<br/>
 * date: 2021/10/28 23:03<br/>
 *
 * @author qkj <br/>
 * @since JDK 1.8
 */


public class ParseController {
    public static void main(String[] args) {
        PeopleBO people = new PeopleBO();
        people.setName("111");

        byte[] bytes = JSON.toJSONString(people).getBytes();
        String s = new String(bytes);
        PeopleBO people1 = JSON.parseObject(s, new TypeReference<PeopleBO>() {
        });
        System.out.println(people1);


        String a = "xxx";
        String s1 = JSON.parseObject(a, new TypeReference<String>() {

        });
        System.out.println(s1);
    }
}