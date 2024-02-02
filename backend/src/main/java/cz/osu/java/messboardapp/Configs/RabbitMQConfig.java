package cz.osu.java.messboardapp.Configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class RabbitMQConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Autowired
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
    //static routing here
    public static final String TOPIC_EXCHANGE_NAME = "topic-exchange";

    public static final String BINDING_KEY_1 = "topic.key.*";
    public static final String BINDING_KEY_2 = "topic.key.#";
    public static final String DIRECT_EXCHANGE_NAME = "direct-exchange";

    public static final String QUEUE_1_NAME = "queue-1";
    public static final String QUEUE_2_NAME = "queue-2";
    public static final String ROUTING_KEY_1 = "routing-key-1";
    public static final String ROUTING_KEY_2 = "routing-key-2";

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }



    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    @Bean
    Queue queue1(){
        return new Queue(QUEUE_1_NAME, true); //good, this is well setup
    }
    @Bean
    Queue queue2(){
        return new Queue(QUEUE_2_NAME, true);
    }

    @Bean
    Binding binding1(Queue queue1, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue1).to(topicExchange).with(ROUTING_KEY_1);
    }

    @Bean
    Binding binding2(Queue queue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue2).to(topicExchange).with(ROUTING_KEY_2);
    }

    @Bean
    public Queue myQueue() {
        Integer msgTimeToLive = 10090; //86400000 ms is a day. too many?
        return QueueBuilder.durable(QUEUE_1_NAME)
                //.ttl(msgTimeToLive) //TTL does not work easily. you'd have to use rules injection
                .build();
    }

}
