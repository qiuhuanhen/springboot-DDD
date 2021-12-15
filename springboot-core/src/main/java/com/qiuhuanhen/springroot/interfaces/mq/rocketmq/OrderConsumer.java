package com.qiuhuanhen.springroot.interfaces.mq.rocketmq;


import cn.vae.singer.framework.mq.annotation.RocketMQConsumerActualizer;
import cn.vae.singer.framework.mq.consumer.impl.DefaultRocketMqConsumer;
import com.qiuhuanhen.springroot.application.bo.OrderBO;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类名称: OrderConsumer
 * 类描述: order消费者
 * @author qiuKaiJian
 * @since 2021/10/29
 */
@Component
/**
 * 定义要接收的主题
 */
@RocketMQConsumerActualizer(topic = "order")
public class OrderConsumer extends DefaultRocketMqConsumer<OrderBO> {


    @Autowired
    private OrderConsumer orderConsumer;

    /**
     * 消费消息 必须重写
     * @param tag 标签
     * @param message 消息
     */
    @Override
    public void handleMessage(String tag, MessageExt message) {
        // 引用super msgConvertObj 方法  转成OrderDTO对象  对象需要继承RocketMqBaseDTO
        OrderBO orderBO = orderConsumer.msgConvertObj(message,OrderBO.class);

        System.out.println("message:"+orderBO.getContext()+"--"+orderBO.getTopic());
    }

    /**
     * 校验消息 可选重写
     * @param tag 标签
     * @param message 消息
     * @return boolean
     */
    @Override
    public boolean validMessage(String tag, MessageExt message) {
        return false;
    }


}
