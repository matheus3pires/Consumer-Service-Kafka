package atividade02.com.br.consumerservicekafka.service;

import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
public class KafkaConsumerService {

    private final KafkaConsumer<String, String> consumer;
    @Getter
    private String lastMessage;

    @Autowired
    public KafkaConsumerService(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
        startConsumer();
    }

    private void startConsumer() {
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    processMessage(record.value());
                }
            }
        });
    }

    private void processMessage(String message) {
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }

}
