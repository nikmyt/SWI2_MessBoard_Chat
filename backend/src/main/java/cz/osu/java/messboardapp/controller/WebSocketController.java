package cz.osu.java.messboardapp.controller;

import cz.osu.java.messboardapp.RabbitMQ.Producer;
import cz.osu.java.messboardapp.RabbitMQ.TextMessageDTO;
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
@RestController
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate template;
    @Autowired
    Producer producer;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody TextMessageDTO textMessageDTO) {
        System.out.println("Message received.");
        //alright lets unpack this
        //1) what do template do: should stay the way it is
        //2) what do producer do:
        template.convertAndSend("/topic/globalChat", 
                makeTheMessageSendable(textMessageDTO.getMessage()));
        producer.sendMessageToQueue1(makeTheMessageSendable(textMessageDTO.getMessage()));
        //alright, so it seems im not in the right channel.
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String makeTheMessageSendable(String message)
    {
        message = "{\"content\": \"" + message + "\"}";
        return message;
    }


    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload TextMessageDTO textMessageDTO) {
        System.out.println("Message sent.");
        //producer.sendMessageToQueue2(textMessageDTO.getMessage());
    }


    //@SendTo("/topic/messages")
    @MessageMapping("/topic/messages")
    @SendToUser
    public TextMessageDTO broadcastMessage(@Payload TextMessageDTO textMessageDTO) {
        return textMessageDTO;
    }
}
