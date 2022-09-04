package com.shf.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RocketMqApplicationTests {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void testSendMessage() {
        rocketMQTemplate.convertAndSend("springboot-rocketmq","hello Spring Boot RocketMQ");
    }

}
