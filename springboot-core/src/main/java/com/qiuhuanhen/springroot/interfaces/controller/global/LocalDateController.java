package com.qiuhuanhen.springroot.interfaces.controller.global;

import com.qiuhuanhen.query.CommonQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * 测试全局日期转换是否正常
 * @see {@link com.qiuhuanhen.springroot.infrastructure.config.DateConfiguration}
 */
@RestController
@RequestMapping("/date")
public class LocalDateController {

    @GetMapping
    public LocalDate test(LocalDate date) {
       return date;
    }

    @GetMapping("/json")
    public LocalDate test1(@RequestBody CommonQuery commonQuery) {
        return commonQuery.getDate();
    }
}
