package com.qiuhuanhen.springroot.infrastructure.annotation.aop;


import com.qiuhuanhen.springroot.application.command.OperCommandService;
import com.qiuhuanhen.springroot.domain.model.OperLogger;
import com.qiuhuanhen.springroot.infrastructure.DO.Oper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class AopAspect {


    @Autowired
    private OperCommandService operCommandServiceImpl;
    @Autowired
    OperLogger operLogger;

    @Pointcut("@annotation(AopAnno)")
    public void doAfterReturning() {

    }

    /**
     * 这里是环绕通知的示例
     * @param point
     * @return
     * @throws Throwable
     * @Around(value = "doAround()")
     * 环绕通知才有 ProceedingJoinPoint
     */



    /**
     * 后置返回通知
     * 这里需要注意的是:
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，
     * 对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     *
     */

    @AfterReturning(value = "doAfterReturning()",returning = "obj")
    public Object addOper(JoinPoint point,Object obj) throws Throwable {

        try {

            ThreadLocal threadLocal = operLogger.getOper();
            Object oper = threadLocal.get();
            System.out.println(Thread.currentThread() + "------thread------");
            System.out.println("切面正在插入数据库" + System.currentTimeMillis());
            operCommandServiceImpl.save((Oper) oper);
            System.out.println("切面插入数据库结束" + System.currentTimeMillis());
            threadLocal.remove();


        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return true;

    }


}
