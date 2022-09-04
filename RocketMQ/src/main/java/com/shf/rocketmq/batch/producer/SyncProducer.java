package com.shf.rocketmq.batch.producer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;

public class SyncProducer {
    @SneakyThrows
    public static void main(String[] args) {
//        1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");

//        2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.120.20:9876;");

//        3.启动producer
        producer.start();

        ArrayList<Message> messages = new ArrayList<>();
        Message msg1 = new Message("BatchTopic", "Tag1", "key1", "Hello World1".getBytes());
        Message msg2 = new Message("BatchTopic", "Tag1", "key1", "Hello World2".getBytes());
        Message msg3 = new Message("BatchTopic", "Tag1", "key1", "Hello World3".getBytes());
        messages.add(msg1);
        messages.add(msg2);
        messages.add(msg3);

        producer.send(messages);

//        6.关闭生产者producer
        producer.shutdown();
    }
}
