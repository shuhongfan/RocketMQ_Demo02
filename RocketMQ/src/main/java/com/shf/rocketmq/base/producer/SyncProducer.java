package com.shf.rocketmq.base.producer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

public class SyncProducer {
    @SneakyThrows
    public static void main(String[] args) {
//        1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");

//        2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.120.20:9876;");

//        3.启动producer
        producer.start();

        for (int i = 0; i < 10; i++) {
//            4.创建消息对象，指定主题Topic、Tag和消息体
            /**
             * 参数一：消息主题Topic
             * 参数二：消息Tag
             * 参数三：消息内容
             */
            Message message = new Message(
                    "base",
                    "Tag1",
                    ("Hello World" + i).getBytes());

//        5.发送消息
            SendResult result = producer.send(message);
//        发送状态
            SendStatus status = result.getSendStatus();
//        消息ID
            String msgId = result.getMsgId();
//        消息接收队列ID
            int queueId = result.getMessageQueue().getQueueId();
            System.out.println("发送状态：" + status);
            System.out.println("消息ID：" + msgId);
            System.out.println("消息接收队列ID：" + queueId);
        }

//        6.关闭生产者producer
        producer.shutdown();
    }
}
