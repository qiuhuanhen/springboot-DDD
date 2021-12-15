package com.qiuhuanhen.springroot.application.command.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuhuanhen.springroot.application.command.TestCommandService;
import com.qiuhuanhen.springroot.infrastructure.DO.TestT;
import com.qiuhuanhen.springroot.infrastructure.mapper.TTestTMapper;
import org.springframework.stereotype.Service;
/**
 * Description: test 增删改业务层<br/>
 * date: 2021/12/6 20:31<br/>
 * @author qkj <br/>
 * @since JDK 1.8
 */
@Service
public class TestCommandServiceImpl extends ServiceImpl<TTestTMapper, TestT> implements TestCommandService {

}
