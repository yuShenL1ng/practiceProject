package com.practice.demo.config;

import com.practice.demo.util.GenerateUUID;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /**
     * RabbitMq配置
     *
     * @author zhuzhe
     * @date 2018/5/25 13:37
     * @email 1529949535@qq.com
     */
        /**
         * 消息交换机的名字
         */
        public static final String EXCHANGE = "exchangeTest";

        public static final String EXCHANGE2 = "fanoutExchange";
        /**
         * 队列key1
         */
        public static final String ROUTINGKEY1 = "TestDirectRouting";
        /**
         * 队列key2
         */
        public static final String ROUTINGKEY2 = "TestDirectRouting2";

        @Autowired
        private QueueConfig queueConfig;
        @Autowired
        private ExchangeConfig exchangeConfig;


        @Autowired
        private MyAckReceiver myAckReceiver;//消息接收处理类


    /**
         * 连接工厂
         */
        @Autowired
        private ConnectionFactory connectionFactory;

        /**
         * 将消息队列1和交换机进行绑定
         */
        @Bean
        public Binding binding_one() {
            return BindingBuilder.bind(queueConfig.firstQueue()).to(exchangeConfig.directExchange()).with(RabbitConfig.ROUTINGKEY1);
        }

        /**
         * 将消息队列2和交换机进行绑定
         */
        @Bean
        public Binding binding_two() {
            return BindingBuilder.bind(queueConfig.secondQueue()).to(exchangeConfig.directExchange()).with(RabbitConfig.ROUTINGKEY2);
        }

        @Bean
        public Binding seed() {
            return BindingBuilder.bind(queueConfig.TestDirectQueue()).to(exchangeConfig.directExchange()).with(RabbitConfig.ROUTINGKEY1);
        }

        @Bean
        Binding bindingExchangeA() {
            return BindingBuilder.bind(queueConfig.queueA()).to(exchangeConfig.fanoutExchange());
        }

        @Bean
        Binding bindingExchangeB() {
            return BindingBuilder.bind(queueConfig.queueB()).to(exchangeConfig.fanoutExchange());
        }

        @Bean
        Binding bindingExchangeC() {
            return BindingBuilder.bind(queueConfig.queueC()).to(exchangeConfig.fanoutExchange());
        }

        /**
         * 定义rabbit template用于数据的接收和发送
         *
         * @return
         */
        @Bean
        public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
            RabbitTemplate rabbitTemplate = new RabbitTemplate();
            rabbitTemplate.setConnectionFactory(connectionFactory);
            //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
            rabbitTemplate.setMandatory(true);

            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
                System.out.println("ConfirmCallback:     "+"原因："+cause);
            });

            rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
                System.out.println("ReturnCallback:     "+"消息："+message);
                System.out.println("ReturnCallback:     "+"回应码："+replyCode);
                System.out.println("ReturnCallback:     "+"回应信息："+replyText);
                System.out.println("ReturnCallback:     "+"交换机："+exchange);
                System.out.println("ReturnCallback:     "+"路由键："+routingKey);
            });

            return rabbitTemplate;
        }

//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        //设置当前消费者的数量
//        container.setConcurrentConsumers(1);
//        //设置最大消费者的数量
//        container.setMaxConcurrentConsumers(5);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
//        //设置一个队列
//        container.setQueueNames("TestDirectQueue");
//        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
//            @Override
//            public String createConsumerTag(String queue) {
//                return queue + "_" + GenerateUUID.getUUID();
//            }
//        });
//        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
//        //  container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");
//        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
//        //container.setQueues(new Queue("TestDirectQueue",true));
//        //container.addQueues(new Queue("TestDirectQueue2",true));
//        //container.addQueues(new Queue("TestDirectQueue3",true));
//        container.setMessageListener(myAckReceiver);
//        return container;
//    }

}