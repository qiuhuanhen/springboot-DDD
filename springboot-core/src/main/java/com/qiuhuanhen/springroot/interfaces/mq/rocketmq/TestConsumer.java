package com.qiuhuanhen.springroot.interfaces.mq.rocketmq;

import cn.vae.singer.framework.mq.annotation.RocketMQConsumerActualizer;
import cn.vae.singer.framework.mq.consumer.impl.DefaultRocketMqConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

@RocketMQConsumerActualizer(topic = "test1")

/**
 * 类名称: TestConsumer
 * 类描述: 测试消费者
 * @author qiuKaiJian
 * @since 2021/10/28
 */
public class TestConsumer extends DefaultRocketMqConsumer {

    @Autowired
    private TestConsumer testConsumer;

    /**
     * 消费消息 (必须重写)
     * @param tag 标签
     * @param message 消息
     * @return ConsumeConcurrentlyStatus状态
     */

    @Override
    public void handleMessage(String tag, MessageExt message) {
        // 转换纯文本消息 引用super
        String msgStr = testConsumer.msgConvertString(message);
        System.out.println(this.getClass().getName()+"---test---收到了消息:"+msgStr+"--------");
    }

    /**
     * 验证消息(方法可选重写) 业务自实现
     * @param tag 标签
     * @param message 消息
     * @return boolean
     */
    @Override
    public boolean validMessage(String tag, MessageExt message) {
        return false;
    }


}
