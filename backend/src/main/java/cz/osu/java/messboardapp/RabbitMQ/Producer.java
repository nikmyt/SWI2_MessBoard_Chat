package cz.osu.java.messboardapp.RabbitMQ;

import cz.osu.java.messboardapp.Configs.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final RabbitTemplate rabbitTemplate;

    final Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
    public Producer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageToQueue1(TextMessageDTO textMessageDTO){
        //rabbitTemplate.setMessageConverter(converter);
        //Message message = MessageBuilder.withBody(makeTheMessageSendable(textMessageDTO).getBytes()).build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_1, textMessageDTO); //im out of ideas. Actually this seems to have fixed the "required content" bug. lol. fixing by breaking
    }

    private String makeTheMessageSendable(TextMessageDTO textMessageDTO)
    {
        String jsoned = TextMessageDTO.toJson(textMessageDTO);
        return jsoned;
    }
    public void sendMessageToQueue2(String messageContent){
        rabbitTemplate.setMessageConverter(converter);
        Message message = MessageBuilder.withBody(messageContent.getBytes()).build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_2, message);
    }
}
