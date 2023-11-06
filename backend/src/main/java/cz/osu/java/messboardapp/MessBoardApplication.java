package cz.osu.java.messboardapp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessBoardApplication.class, args);
		//required for functionality
	}
	public static final String topicExchangeName = "spring-boot-exchange";
	static final String queueName = "spring-boot";

	@Bean
	Queue queue()
	{
		return new Queue(queueName, false);
	}

	@Bean
	ConnectionFactory connectionFactory()
	{
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		return connectionFactory;
	}

}