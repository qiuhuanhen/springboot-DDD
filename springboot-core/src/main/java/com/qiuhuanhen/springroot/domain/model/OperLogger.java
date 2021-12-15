package com.qiuhuanhen.springroot.domain.model;

import com.qiuhuanhen.springroot.infrastructure.DO.Oper;
import org.springframework.stereotype.Component;

/**
* Description: 操作日志传输实体bean 存放于ThreadLocal 保障线程安全<br/>
* date: 2021/12/6 20:35<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
@Component
public class OperLogger {
    ThreadLocal threadLocal = new ThreadLocal();


    public Oper storeLog(String str) {
        Oper op = new Oper();
        op.setTestDesc(str);
        threadLocal.set(op);
        return op;
    }


    public ThreadLocal getOper() {
        return this.threadLocal;
    }


}
