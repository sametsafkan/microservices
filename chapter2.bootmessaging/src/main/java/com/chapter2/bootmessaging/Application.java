package com.chapter2.bootmessaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//CommandLineRunner class ının run methodunu mesajlaşmayı başlatmak için kullanıyoruz
@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired
	Sender sender;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		sender.send("Hello Messaging..!!!");
	}
}

@Component
class Sender {
	//RabbitMessagingTemplate ile springboot mesaj göndermek için gerekli olan bütün konfigürasyonları yapıyor.
	@Autowired
	RabbitMessagingTemplate template;

	@Bean
	Queue queue() {
		return new Queue("TestQ", false);
	}

	public void send(String message) {
		template.convertAndSend("TestQ", message);
	}
}

@Component
class Receiver {
	//rabbitListener annotation u ile Spring Book mesaj almak için gerekli bütün konfigürasyonları otomatik olarak yapar.s
	@RabbitListener(queues = "TestQ")
	public void processMessage(String content) {
		System.out.println(content);
	}
}