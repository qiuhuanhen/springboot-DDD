package com.qiuhuanhen.springroot.application.query.impl;

import com.qiuhuanhen.springroot.application.query.DynamicQueryService;
import org.springframework.stereotype.Service;


/**
* Description: 动态数据库查询业务类 <br/>
* date: 2021/12/6 21:25<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
@Service
//@DS("db1")
public class DynamicQueryServiceImpl implements DynamicQueryService {
    //    @DS("db1")
    @Override
    public Integer getCount() {
        return null;
    }
}