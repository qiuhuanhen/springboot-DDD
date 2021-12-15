package com.qiuhuanhen.springroot.application.query.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuhuanhen.springroot.application.query.TestQueryService;
import com.qiuhuanhen.springroot.infrastructure.DO.TestT;
import com.qiuhuanhen.springroot.infrastructure.mapper.TTestTMapper;
import org.springframework.stereotype.Service;

/**
* Description: Test查询业务类<br/>
* date: 2021/12/6 22:39<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
@Service
public class TestQueryServiceImpl extends ServiceImpl<TTestTMapper, TestT> implements TestQueryService {

}
