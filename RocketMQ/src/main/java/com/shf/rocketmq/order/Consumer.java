package com.shf.rocketmq.order;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    @SneakyThrows
    public static void main(String[] args) {
        //1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");

        //2.指定Nameserver地址
        consumer.setNamesrvAddr("192.168.120.20:9876");

        //3.订阅主题Topic和Tag
        consumer.subscribe("OrderTopic", "*");

        //设定消费模式：负载均衡|广播模式
//        consumer.setMessageModel(MessageModel.BROADCASTING);

        //4.注册消息监听器
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : list) {
                    System.out.println("线程名称：" + Thread.currentThread().getName() + "消费消息："+msg);
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        //5.启动消费者consumer
        consumer.start();
    }
}
