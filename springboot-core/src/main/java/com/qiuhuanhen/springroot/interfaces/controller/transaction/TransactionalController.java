package com.qiuhuanhen.springroot.interfaces.controller.transaction;


import com.qiuhuanhen.springroot.application.command.OperCommandService;
import com.qiuhuanhen.springroot.application.command.TestCommandService;
import com.qiuhuanhen.springroot.infrastructure.DO.Oper;
import com.qiuhuanhen.springroot.infrastructure.DO.TestT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tran")
@Controller
public class TransactionalController {
    @Autowired
    private OperCommandService operCommandServiceImpl;
    @Autowired
    private TestCommandService testCommandServiceImpl;

    @Autowired
    private TransactionalController transactionalController;
    @GetMapping
    @Transactional(rollbackFor = Exception.class)
    public void testA() {

        Oper oper = new Oper();
        oper.setTestDesc("tran");
        oper.setTestXxx("testTran");
        operCommandServiceImpl.save(oper);
        // 如果写法是testB() 则testB事务注解失效 必须代理对象执行
        transactionalController.testB();
    }


    /**
     * 注： never : 不加入事务 当存在事务时抛异常
     * @author qkj
     */
    @Transactional(propagation = Propagation.NEVER,rollbackFor = Exception.class)

    public void testB() {
        TestT t = new TestT();
        t.setMark(00000000);
        t.setDes("tran");
        testCommandServiceImpl.save(t);
        System.out.println("执行完毕");
    }
}
