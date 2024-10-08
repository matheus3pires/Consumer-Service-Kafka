package atividade02.com.br.consumerservicekafka.service;

import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumerService {

    @Autowired
    private static List<String> messages;

    public KafkaConsumerService(List<String> messages) {
        this.messages = messages;
    }

    @KafkaListener(topics = "school-kafka",containerFactory = "kafkaListenerContainerFactory")
    public void consumeBatch(List<String> messages) {
        System.err.println("Recebido: " + messages);
        processBatch(messages);
    }

    private void processBatch(List<String> mensagens) {
        mensagens.forEach(
                mensagem -> {
                    System.err.println("Processando: " + mensagem);
                    messages.add(mensagem);
                }
        );
    }

    public static Map<String, String> pegarLista(){
        Map<String, String> notification = new HashMap<>();
        messages.forEach(message -> notification.put("message", message));
        messages.clear();
        return notification;
    }
}
