package com.qiuhuanhen.springroot.application.query.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.qiuhuanhen.springroot.application.query.OperQueryService;
import com.qiuhuanhen.springroot.infrastructure.DO.Oper;
import com.qiuhuanhen.springroot.infrastructure.mapper.OperMapper;
import org.springframework.stereotype.Service;

/**
* Description:Oper查询业务类 <br/>
* date: 2021/12/6 22:39<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
@Service
public class OperQueryServiceImpl extends ServiceImpl<OperMapper, Oper> implements OperQueryService {

}
