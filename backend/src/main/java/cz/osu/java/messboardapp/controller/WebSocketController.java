package cz.osu.java.messboardapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Controller
@RequestMapping("/api")
public class WebSocketController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public String handleWebSocketMessage(String message)
    {
        System.out.println("received message: " + message);
        return "Message received: " + message;
    }
}
