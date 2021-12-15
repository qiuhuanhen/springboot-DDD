package com.qiuhuanhen.springroot.application.bo;


import com.qiuhuanhen.springroot.infrastructure.annotation.Contain;

/**
* Description: People业务传输对象 <br/>
* date: 2021/12/6 20:30<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
public class PeopleBO {
    @Contain(code = "1;2;3")
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
