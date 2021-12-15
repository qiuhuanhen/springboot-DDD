package com.qiuhuanhen.springroot.interfaces.controller.schedule;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component

/**
 * @see {@link com.boot.config.ScheduledTaskConfig}
 */
public class ScheduleController {
    /** 秒分时日月周  **/
    @Scheduled(cron = "* 30 * * * *")
    @SchedulerLock(name = "aa", lockAtMostFor = "1m", lockAtLeastFor = "5s")
    public void test() {
        System.out.println("==这里是定时器 可以项目全局搜索文字找到==");
    }
}
