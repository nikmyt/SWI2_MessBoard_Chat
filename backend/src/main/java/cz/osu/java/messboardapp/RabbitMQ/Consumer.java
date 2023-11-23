package cz.osu.java.messboardapp.RabbitMQ;

import com.rabbitmq.client.Channel;
import cz.osu.java.messboardapp.Configs.RabbitMQConfig;
import cz.osu.java.messboardapp.controller.WebSocketController;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private WebSocketController socket;
    @RabbitListener(queues = RabbitMQConfig.QUEUE_1_NAME)
    public void receiveFromQueue1(Message message, Channel channel) throws IOException {
        try{
            String messageContent = new String(message.getBody());
            System.out.println("Received from Queue 1: " + messageContent);

            // Forward the message to connected WebSocket clients
            messagingTemplate.convertAndSend("/topic/queue1", messageContent);
            //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception ex){
            ex.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_2_NAME)
    public void receiveFromQueue2(Message message, Channel channel) throws  IOException{
        try {
            String messageContent = new String(message.getBody());
            //messageContent = messageContent.substring(13, messageContent.length()-2);
            System.out.println("Received from Queue 2: " + messageContent);
            TextMessageDTO textMessageDTO = new TextMessageDTO(messageContent);
            // Forward the message to connected WebSocket clients
            messagingTemplate.convertAndSend("/topic/message", textMessageDTO);
            //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception ex){
            ex.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true );
        }
    }
}
