package com.practice.demo.Rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DirectReceiver {

    @RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
    public void process(Map testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "fanout.A")//监听的队列名称 TestDirectQueue
    public void fanoutA(Map testMessage) {
        System.out.println("A消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "fanout.B")//监听的队列名称 TestDirectQueue
    public void fanoutB(Map testMessage) {
        System.out.println("B消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "fanout.C")//监听的队列名称 TestDirectQueue
    public void fanoutC(Map testMessage) {
        System.out.println("C消费者收到消息  : " + testMessage.toString());
    }

}
