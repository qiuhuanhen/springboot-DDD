package com.qiuhuanhen.springroot.interfaces.mq.rocketmq;

import cn.vae.singer.framework.mq.producer.DefaultRocketMqProducer;
import com.alibaba.fastjson.JSON;
import com.qiuhuanhen.springroot.application.bo.OrderBO;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称: OrderProducer
 * 类描述: order生产者
 * @author qiuKaiJian
 * @since 2021/10/28
 */
@RestController
@RequestMapping("/order")
public class OrderProducer {


    @Autowired
    private DefaultRocketMqProducer defaultRocketMqProducer;

    @GetMapping
    /**
     * 消息发送样例 (发送字符串对象)
     */
    public void test() {

        try {
            defaultRocketMqProducer.sendAsync(
                    JSON.toJSONString(new OrderBO("order-msg")),
                    "order",
                    "testTags",
                    new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("--order--消息发送成功--------");
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("--order---消息发送失败------");
                }
            });
        } catch (MQClientException | RemotingException | InterruptedException e) {
            e.printStackTrace();
        }


    }

}
