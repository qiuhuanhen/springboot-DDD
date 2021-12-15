package com.qiuhuanhen.springroot.interfaces.controller.database;

import com.qiuhuanhen.springroot.application.query.DynamicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* Description: 动态数据源测试 <br/>
* date: 2021/12/6 23:12<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
@RestController
@RequestMapping("/dynamic")
public class DynamicDataSourceController  {

    @Autowired
    private DynamicQueryService dynamicService;

    @GetMapping
    public Integer getCount() {
        Integer count = dynamicService.getCount();
        return count;
    }
}
