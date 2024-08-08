package io.message.message.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.TransactionManager;

import java.util.List;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Value("${consumers.topics.publish-message}")
    private String publishMessageTopic;

    @Value("${consumers.topics.pending-message}")
    private String pendingMessageTopic;

    @Bean
    public ReceiverOptions<String, String> receiverOptions() {
        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties(new DefaultSslBundleRegistry()));
        return receiverOptions.subscription(List.of(publishMessageTopic, pendingMessageTopic));
    }

    @Bean
    public SenderOptions<String, String> senderOptions() {
        return SenderOptions.create(kafkaProperties.buildProducerProperties(new DefaultSslBundleRegistry()));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate reactiveKafkaConsumerTemplate() {
        return new ReactiveKafkaConsumerTemplate<>(receiverOptions());
    }

    @Bean
    public ReactiveKafkaProducerTemplate reactiveKafkaProducerTemplate() {
        return new ReactiveKafkaProducerTemplate<>(senderOptions());
    }

    @Bean
    public TransactionManager reactiveKafkaProducerTemplate(ReactiveKafkaProducerTemplate reactiveKafkaProducerTemplate) {
        return reactiveKafkaProducerTemplate.transactionManager();
    }
}
