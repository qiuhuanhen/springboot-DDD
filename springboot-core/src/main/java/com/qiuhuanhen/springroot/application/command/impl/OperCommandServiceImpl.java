package com.qiuhuanhen.springroot.application.command.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.qiuhuanhen.springroot.application.command.OperCommandService;
import com.qiuhuanhen.springroot.infrastructure.DO.Oper;
import com.qiuhuanhen.springroot.infrastructure.mapper.OperMapper;
import org.springframework.stereotype.Service;

/**
 * Description: Oper增删改业务层<br/>
 * date: 2021/12/6 20:31<br/>
 * @author qkj <br/>
 * @since JDK 1.8
 */
@Service
public class OperCommandServiceImpl extends ServiceImpl<OperMapper, Oper> implements OperCommandService {

}
