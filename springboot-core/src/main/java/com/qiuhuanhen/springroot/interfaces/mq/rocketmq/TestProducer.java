package com.qiuhuanhen.springroot.interfaces.mq.rocketmq;

import cn.vae.singer.framework.mq.producer.DefaultRocketMqProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称: TestProducer
 * 类描述: 测试生产者
 * @author qiuKaiJian
 * @since 2021/10/28
 */
@RestController
@RequestMapping("/producer")
public class TestProducer {


    @Autowired
    private DefaultRocketMqProducer defaultRocketMqProducer;

    /**
     * 测试发送纯字符串消息
     */
    @GetMapping
    public void test() {


        try {
            defaultRocketMqProducer.sendAsync("x纯字符串x","test1","tags", new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("消息发送成功");
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println(throwable.getMessage());
                    System.out.println("消息发送失败");
                }
            });
        } catch (MQClientException | RemotingException | InterruptedException e) {
            e.printStackTrace();
        }


    }

}
