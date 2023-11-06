package cz.osu.java.messboardapp.Configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    String deliveryQueue = "delivery_queue_fanout";

    String emailQueue = "email_queue_fanout";

    String cartExchange = "cart_exchange";

    @Bean
    Queue deliveryQueue() {
        return new Queue(deliveryQueue, false);
    }

    @Bean
    Queue emailQueue() {
        return new Queue(emailQueue, false);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(cartExchange);
    }

    @Bean
    Binding deliveryBinding(Queue deliveryQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(deliveryQueue).to(exchange);
    }

    @Bean
    Binding emailBinding(Queue emailQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(emailQueue).to(exchange);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
