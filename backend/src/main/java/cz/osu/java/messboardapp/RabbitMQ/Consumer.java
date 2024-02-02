package cz.osu.java.messboardapp.RabbitMQ;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import cz.osu.java.messboardapp.Configs.RabbitMQConfig;
import cz.osu.java.messboardapp.controller.WebSocketController;
import cz.osu.java.messboardapp.model.ChatMessage;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {

    private final AppUserRepository appUserRepository;

    @Autowired
    public Consumer(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private WebSocketController socket;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_1_NAME, concurrency = "3-5") //The concurrency attribute can specify a range of consumers that can be spawned to process messages concurrently.
    public void receiveFromQueue1(@Payload TextMessageDTO textMessageDTO, Channel channel) throws IOException {
        try{
            Long destinationId = textMessageDTO.getDestinationId();
            System.out.println("Message received with routing key: " + destinationId);
            System.out.println("Message received: " + textMessageDTO.getText());
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setId(textMessageDTO.getId());
            chatMessage.setText(textMessageDTO.getText());
            chatMessage.setDestination(textMessageDTO.getDestinationId());
            chatMessage.setSender(textMessageDTO.getSenderId());
            chatMessage.setExtra(appUserRepository.findBoardUserByUserId(chatMessage.getSenderId()).getUsername());
            chatMessage.setTimestamp(textMessageDTO.getTimestamp());
            System.out.println(chatMessage);
            messagingTemplate.convertAndSend("/topic/" + destinationId, chatMessage);
            //messagingTemplate.convertAndSend(String.valueOf(destinationId), makeTheMessageSendable(message));
            //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception ex){
            ex.printStackTrace();
            //channel.basicNack(textMessageDTO.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    private String makeTheMessageSendable(String message)
    {
        System.out.println("PreJsoned: " + message);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String jsonified = gson.toJson(message);
        System.out.println("PostJsoned: " + jsonified); //TODONE: does it look correct? YES!
        return jsonified;
    }
    /*
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
     */
}
