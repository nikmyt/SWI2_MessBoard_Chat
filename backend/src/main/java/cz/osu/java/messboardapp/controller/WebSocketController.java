package cz.osu.java.messboardapp.controller;

import cz.osu.java.messboardapp.Configs.DynamicRoutingDataSource;
import cz.osu.java.messboardapp.RabbitMQ.Producer;
import cz.osu.java.messboardapp.RabbitMQ.TextMessageDTO;
import cz.osu.java.messboardapp.model.ChatMessage;
import cz.osu.java.messboardapp.repository.ChatMessageRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class WebSocketController {
    @Autowired
    private DynamicRoutingDataSource dynamicRoutingDataSource;
    @Autowired
    SimpMessagingTemplate template;
    @Autowired
    Producer producer;

    private ChatMessageRepository chatMessageRepository;

    //oddly enough may be correctly setup. pls check.
    @PostConstruct
    public void init() {
        dynamicRoutingDataSource.setDataSource("sqlite");
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody TextMessageDTO textMessageDTO) {
        System.out.println("Message received.");
        System.out.println(TextMessageDTO);
        //11.1 need to parse message, find out the destination, and send the message accordingly
        String gimmeDestination = textMessageDTO.getDestination();
        //now we hinge on the fact that rabbit needs to be able to create new topics automatically
        //  "/topic/globalChat"
        template.convertAndSend(gimmeDestination,
                makeTheMessageSendable(textMessageDTO));
        //extemely problematic but maybe we can sidestep the problem and send everything to 1 queue anyway.
        producer.sendMessageToQueue1(makeTheMessageSendable(textMessageDTO));

        saveMessageToDisk(textMessageDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String makeTheMessageSendable(TextMessageDTO message)
    {
        String jsoned = "{\"content\": \"" + message.toJson() + "\"}";
        System.out.println(jsoned); //TODO: does it look correct? check please
        //likely not, because it used to send a string. now it send what? whole ass json obj
        //i guess i need to sift garbage again
        //13.1 just as i predicted, GIGO.
        return jsoned;
    }

    private void saveMessageToDisk(TextMessageDTO textMessageDTO) {
        // Convert TextMessageDTO to ChatMessage entity
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setDestination(textMessageDTO.getDestination());
        chatMessage.setTimestamp(LocalDateTime.now().toString());
        chatMessage.setSender(textMessageDTO.getSender());
        chatMessage.setText(textMessageDTO.getText());
        chatMessage.setExtra(textMessageDTO.getExtra());

        chatMessageRepository.save(chatMessage);
    }


    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload TextMessageDTO textMessageDTO) {
        //unused?
        System.out.println("Message sent.");
        //producer.sendMessageToQueue2(textMessageDTO.getMessage());
    }


    //likely does not work/unused
    //@SendTo("/topic/messages")
    @MessageMapping("/topic/messages")
    @SendToUser
    public TextMessageDTO broadcastMessage(@Payload TextMessageDTO textMessageDTO) {
        return textMessageDTO;
    }
}
