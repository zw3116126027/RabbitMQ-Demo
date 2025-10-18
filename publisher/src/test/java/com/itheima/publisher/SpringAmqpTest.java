package com.itheima.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;


@SpringBootTest
public class SpringAmqpTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
     void testSendMessage() {
        String queueName = "simple.queue";
        String message = "hello spring amqp!!!!!!!!!!!!!!!!";
        rabbitTemplate.convertAndSend(queueName,message);
    }
    @Test
     void testWorkQueue() throws InterruptedException {
        String queueName = "work.queue";
        for (int i = 1; i <= 50; i++){
            String message = "hello work queue message"+i;
            rabbitTemplate.convertAndSend(queueName,message);
            Thread.sleep(20);
        }
    }
    @Test
    void testFanout() {
        String exchangeName = "zw.fanout";
        String message = "hello everyone";
        rabbitTemplate.convertAndSend(exchangeName,"",message);
    }
    @Test
    void testSendDirect() {
        String exchangeName = "zw.direct";
        String message = "发送黄色消息";
        rabbitTemplate.convertAndSend(exchangeName,"yellow",message);
    }
    @Test
    void testSendTopic() {
        String exchangeName = "zw.topic";
        String message = "中国新闻";
        rabbitTemplate.convertAndSend(exchangeName,"china.news",message);
    }
    @Test
    void testSendObject() {
        HashMap<Object, Object> msg = new HashMap<>();
        msg.put("name","zhangsan");
        msg.put("age",18);
        rabbitTemplate.convertAndSend("object.queue",msg);
    }
}
