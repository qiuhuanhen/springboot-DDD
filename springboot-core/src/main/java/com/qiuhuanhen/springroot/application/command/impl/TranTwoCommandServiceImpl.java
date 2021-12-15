package com.qiuhuanhen.springroot.application.command.impl;

import com.qiuhuanhen.springroot.infrastructure.DO.TestT;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: Oper DO的事务处理测试类2  <br/>
 * date: 2021/12/6 20:34<br/>
 * @author qkj <br/>
 * @since JDK 1.8
 */
@Service
public class TranTwoCommandServiceImpl extends TestCommandServiceImpl {

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void test() {
        TestT testT = new TestT();
        testT.setDes("绩效被扣");
        System.out.println("事务2进行 员工绩效降低 插入绩效变动记录表.....");
        super.save(testT);
        System.out.println("事务2结束 绩效已降低.....");
    }
}
