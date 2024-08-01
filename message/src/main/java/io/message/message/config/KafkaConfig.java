package io.message.message.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String BOOTSTRAPSERVERS;

    @Bean
    public ReceiverOptions<String, String> receiverOptions() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAPSERVERS);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return ReceiverOptions.create(config);
    }

    @Bean
    public SenderOptions<String, String> senderOptions() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAPSERVERS);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return SenderOptions.create(config);
    }

    @Bean
    public ReactiveKafkaConsumerTemplate reactiveKafkaConsumerTemplate() {
        return new ReactiveKafkaConsumerTemplate<>(receiverOptions());
    }

    @Bean
    public ReactiveKafkaProducerTemplate reactiveKafkaProducerTemplate() {
        return new ReactiveKafkaProducerTemplate<>(senderOptions());
    }
}