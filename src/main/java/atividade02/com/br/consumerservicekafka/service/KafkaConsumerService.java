package atividade02.com.br.consumerservicekafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
public class KafkaConsumerService {

    private final KafkaConsumer<String, String> consumer;

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
        System.out.println(message);
    }

//    @KafkaListener(topics = "school-topic", containerFactory = "kafkaListenerContainerFactory")
//    public void consumeMessage(String message) {
//        processMessage(message);
//    }
//
//    private void processMessage(String message) {
//        System.err.println("Processando mensagem: " + message);
//    }

//    // Listas para armazenar mensagens recebidas
//    private final List<String> studentMessages = new ArrayList<>();
//    private final List<String> teacherMessages = new ArrayList<>();
//
//    @KafkaListener(topics = "school-topic", groupId = "student-group", containerFactory = "kafkaListenerContainerFactory")
//    public void consumeStudentBatch(List<String> messages) {
//        studentMessages.addAll(messages);
//        processMessages(studentMessages); // Processa todas as mensagens recebidas
//    }
//
//    @KafkaListener(topics = "school-topic", groupId = "teacher-group", containerFactory = "kafkaListenerContainerFactory")
//    public void consumeTeacherBatch(List<String> messages) {
//        teacherMessages.addAll(messages);
//        processMessages(teacherMessages); // Processa todas as mensagens recebidas
//    }
//
//    /**
//     * Processa as mensagens, tanto em lotes de 5 e 10 quanto individualmente.
//     *
//     * @param messages Lista de mensagens recebidas.
//     */
//    private void processMessages(List<String> messages) {
//        // Processa mensagens em lotes de 10
//        while (messages.size() >= 10) {
//            List<String> batch10 = messages.subList(0, 10);
//            processBatch(batch10, 10);
//            messages.subList(0, 10).clear(); // Remove as mensagens processadas
//        }
//
//        // Processa mensagens em lotes de 5
//        while (messages.size() >= 5) {
//            List<String> batch5 = messages.subList(0, 5);
//            processBatch(batch5, 5);
//            messages.subList(0, 5).clear(); // Remove as mensagens processadas
//        }
//
//        // Processa mensagens individuais restantes
//        for (String message : messages) {
//            processIndividualMessage(message);
//        }
//        messages.clear(); // Limpa a lista após o processamento
//    }
//
//    private void processBatch(List<String> mensagens, int batchSize) {
//        mensagens.forEach(mensagem -> {
//            System.err.println("Processando em lote de " + batchSize + ": " + mensagem);
//        });
//    }
//
//    private void processIndividualMessage(String mensagem) {
//        System.err.println("Processando individualmente: " + mensagem);
//    }
}
