package com.qiuhuanhen.springroot.application.command.impl;


import com.qiuhuanhen.springroot.infrastructure.DO.Oper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* Description: Oper DO的事务处理测试类1  <br/>
* date: 2021/12/6 20:34<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
@Service
public class TranOneCommandServiceImpl extends OperCommandServiceImpl {

    @Autowired
    private TranOneCommandServiceImpl tranOneServiceImpl;

    @Autowired
    private TranTwoCommandServiceImpl tranTwoServiceImpl;

    /**
     * 员工摸鱼被抓
     */
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        Oper oper = new Oper();
        oper.setTestXxx("摸鱼被抓");
        // 生产环境必须使用log记录 而不是输出控制台
        System.out.println("事务1进行 员工摸鱼 插入摸鱼名单表.....");
        super.save(oper);


        // 绩效业务
        tranTwoServiceImpl.test();
        System.out.println("事务1结束 摸鱼名单异常........");
        // do other 模拟出现异常
        int a = 1/0;
    }

}
