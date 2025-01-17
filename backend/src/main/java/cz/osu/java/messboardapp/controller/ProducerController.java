package cz.osu.java.messboardapp.controller;

import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.service.MessageGeneratorService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProducerController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public String exchange = "cart_exchange";
    @PostMapping("/message/send")
    public String getMessage(@RequestBody BoardUser user) {
        amqpTemplate.convertAndSend(exchange, "", user);
        return "message received";
    }


}
