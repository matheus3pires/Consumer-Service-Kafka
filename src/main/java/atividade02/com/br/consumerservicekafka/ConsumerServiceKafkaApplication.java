package atividade02.com.br.consumerservicekafka;

import atividade02.com.br.consumerservicekafka.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerServiceKafkaApplication {

	@Autowired
	private KafkaConsumerService kafkaConsumerService;

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServiceKafkaApplication.class, args);
	}

}
