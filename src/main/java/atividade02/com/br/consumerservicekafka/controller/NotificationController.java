package atividade02.com.br.consumerservicekafka.controller;

import atividade02.com.br.consumerservicekafka.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class NotificationController {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @GetMapping("/api/notification")
    public ResponseEntity<Map<String, String>> getNotification() {
        return ResponseEntity.ok(KafkaConsumerService.pegarLista());
    }
}



