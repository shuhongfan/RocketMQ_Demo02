package com.shf.rocketmq.batch.consumer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
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
        consumer.subscribe("BatchTopic", "Tag1 || Tag2");

        //设定消费模式：负载均衡|广播模式
//        consumer.setMessageModel(MessageModel.BROADCASTING);

        //4.设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            //接受消息内容
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("consumeThread=" + Thread.currentThread().getName() + "," + new String(msg.getBody())+",延迟时间："+(System.currentTimeMillis()-msg.getStoreTimestamp()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //5.启动消费者consumer
        consumer.start();
    }
}
