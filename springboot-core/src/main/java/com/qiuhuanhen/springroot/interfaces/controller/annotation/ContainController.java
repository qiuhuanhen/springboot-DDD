package com.qiuhuanhen.springroot.interfaces.controller.annotation;

import com.qiuhuanhen.springroot.application.bo.PeopleBO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: contain自定义注解测试(自定义注解作用在Bo实体类字段上)
 * {@link PeopleBO}
 * @see com.qiuhuanhen.springroot.infrastructure.annotation.ContainValidator
 * date: 2021/3/17
 * @author:qkj
 * @since JDK 1.8
 */

@RequestMapping("/contain")
@RestController
public class ContainController {
    @RequestMapping("/test")
    public PeopleBO test(@Validated @RequestBody PeopleBO people) {
        people.setAge(18);
        return people;
    }
}
