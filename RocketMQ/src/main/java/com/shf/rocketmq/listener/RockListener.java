package com.shf.rocketmq.listener;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


@RocketMQMessageListener(
        topic = "springboot-rocketmq",
        consumeMode = ConsumeMode.ORDERLY,
        consumerGroup = "${rocketmq.producer.group}")
@Component
public class RockListener implements RocketMQListener<String> {

        @Override
        public void onMessage(String message) {
            System.out.println("message = " + message);
        }
}
