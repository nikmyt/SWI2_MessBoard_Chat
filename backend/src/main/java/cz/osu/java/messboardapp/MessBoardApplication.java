package cz.osu.java.messboardapp;

import cz.osu.java.messboardapp.RabbitMQ.Producer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessBoardApplication.class, args);
		//required for functionality
	}

	@Bean
	CommandLineRunner demo(Producer producer){
		return args -> {
			String string1 = "{\"content\": \"Hello from Queue 1!\"}";
			String string2 = "{\"content\": \"Greetings from Queue 2!\"}";
			producer.sendMessageToQueue1(string1);
			producer.sendMessageToQueue2(string2);
		};
	}




}