package com.qiuhuanhen.springroot.interfaces.controller.transaction;

import com.qiuhuanhen.springroot.application.command.impl.TranOneCommandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务传播机制 模拟
 * @author qkj
 */

@RestController
@RequestMapping("/hard")
public class TscationHarderController {

    @Autowired
    private TranOneCommandServiceImpl tranOneServiceImpl;

    /**
     * 记一次员工摸鱼被抓的惩罚操作
     */
    @GetMapping
    public void doSomeThing() {
        tranOneServiceImpl.test();
    }


}
