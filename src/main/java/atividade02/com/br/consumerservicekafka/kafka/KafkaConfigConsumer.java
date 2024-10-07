package atividade02.com.br.consumerservicekafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@EnableKafka
@Configuration
public class KafkaConfigConsumer {

    @Bean
    public KafkaConsumer<String, String> kafkaConsumer() {
        String topicName = "school-kafka";

        Properties propsConsumer = new Properties();
        propsConsumer.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        propsConsumer.put(ConsumerConfig.GROUP_ID_CONFIG, "basic-group");
        propsConsumer.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propsConsumer.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propsConsumer.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //TODO _ PARTE NOVA - arrumar a parte de consumer.subscribe q aparenta ser errado -> group_ID_CONFIG - topic
        propsConsumer.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(propsConsumer);
        consumer.subscribe(Collections.singleton(topicName));
        return consumer;
    }


}
