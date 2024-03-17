package cz.osu.java.messboardapp.controller;

import cz.osu.java.messboardapp.Configs.DynamicRoutingDataSource;
import cz.osu.java.messboardapp.Form.MessageForm;
import cz.osu.java.messboardapp.RabbitMQ.Producer;
import cz.osu.java.messboardapp.RabbitMQ.TextMessageDTO;
import cz.osu.java.messboardapp.model.ChatMessage;
import cz.osu.java.messboardapp.repository.ChatMessageRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
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

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    //oddly enough may be correctly setup. pls check.
    @PostConstruct
    public void init() {
        dynamicRoutingDataSource.setDataSource("sqlite");
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody MessageForm messageForm) {
        TextMessageDTO message = new TextMessageDTO(messageForm.getDestinationId(), messageForm.getTimestamp(), messageForm.getSenderId(), messageForm.getText(),messageForm.getExtra());

        message.setId(saveMessageToDisk(message)); //this could be fine unironically
        //template.convertAndSend(String.valueOf(destinationId), makeTheMessageSendable(message));
        //extemely problematic but maybe we can sidestep the problem and send everything to 1 queue anyway.
        producer.sendMessageToQueue1(message);


        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Long saveMessageToDisk(TextMessageDTO textMessageDTO) {
        // Convert TextMessageDTO to ChatMessage entity
        //15.1 TODO: chatrepo is null for some reason
        //perhaps bc the db file doesn't exist
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setDestination(textMessageDTO.getDestinationId());
        chatMessage.setTimestamp(textMessageDTO.getTimestamp());
        chatMessage.setSender(textMessageDTO.getSenderId());
        chatMessage.setText(textMessageDTO.getText());
        chatMessage.setExtra(textMessageDTO.getExtra());

        Long messageId = chatMessageRepository.save(chatMessage).getId();
        return messageId;
    }


    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload TextMessageDTO textMessageDTO) {
    }


    //likely does not work/unused
    //@SendTo("/topic/messages")
    @MessageMapping("/topic/messages")
    @SendToUser
    public TextMessageDTO broadcastMessage(@Payload TextMessageDTO textMessageDTO) {
        return textMessageDTO;
    }
}
