package com.qiuhuanhen.springroot.interfaces.controller.current;

import com.qiuhuanhen.springroot.application.command.TestCommandService;
import com.qiuhuanhen.springroot.infrastructure.DO.TestT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController

@RequestMapping("/lock")
/**
 * 锁 解决高并发问题 demo
 * @author qkj
 */
public class LockController implements Runnable {

    Lock lock = new ReentrantLock();

    int count = 20;

    @Autowired
    @Qualifier("testCommandServiceImpl")
    /** 等效于 @Autowired private TestCommandService testCommandServiceImpl 因为spring找bean先byType再byName **/
    private TestCommandService testCommandService;


    /**
     * 结论： 多线程下 用synchronized 、lock锁可以解决线程安全问题
     * 总结： 不同bean之间 用threadLocal包装传输  同方法之间 用锁即可
     */
    @Override
    public void run() {
        lock.lock();
        try {

            TestT test = new TestT();

            test.setDes(String.valueOf(count));
            test.setMark(count);
            testCommandService.save(test);
            count--;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 锁【lock.lock】必须紧跟try代码块，且unlock要放到finally第一行。
     * Inspection info:
     * 在使用阻塞等待获取锁的方式中，必须在try代码块之外，并且在加锁方法与try代码块之间没有任何可能抛出异常的方法调用，避免加锁成功后，在finally中无法解锁。
     * 说明一：如果在lock方法与try代码块之间的方法调用抛出异常，那么无法解锁，造成其它线程无法成功获取锁。
     * 说明二：如果lock方法在try代码块之内，可能由于其它方法抛出异常，导致在finally代码块中，unlock对未加锁的对象解锁，它会调用AQS的tryRelease方法（取决于具体实现类），抛出IllegalMonitorStateException异常。
     * 说明三：在Lock对象的lock方法实现中可能抛出unchecked异常，产生的后果与说明二相同。 java.concurrent.LockShouldWithTryFinallyRule.rule.desc
     */
    @GetMapping
    public void test() {

        try {
            for (int i = 0; i < 20; i++) {
                new Thread(this).start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

//        try {
//            TestT test = new TestT();
//
//            test.setDes(String.valueOf(count));
//            test.setMark(count);
//
//            testService.save(test);
//            count--;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        lock.unlock();


    }
}
