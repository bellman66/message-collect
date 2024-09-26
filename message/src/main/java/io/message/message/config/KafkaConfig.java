package io.message.message.config;

import io.message.message.MessageTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.List;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Bean("publishMessageReceiverOptions")
    public ReceiverOptions<String, String> publishMessageReceiverOptions() {
        ReceiverOptions<String, String> receiverOptions =
                ReceiverOptions.create(
                        kafkaProperties.buildConsumerProperties(new DefaultSslBundleRegistry()));
        return receiverOptions.subscription(List.of(MessageTopic.PUBLISH.name()));
    }

    @Bean("pendingMessageReceiverOptions")
    public ReceiverOptions<String, String> pendingMessageReceiverOptions() {
        ReceiverOptions<String, String> receiverOptions =
                ReceiverOptions.create(
                        kafkaProperties.buildConsumerProperties(new DefaultSslBundleRegistry()));
        return receiverOptions.subscription(List.of(MessageTopic.PENDING.name()));
    }

    @Bean
    public SenderOptions<String, String> senderOptions() {
        return SenderOptions.create(
                kafkaProperties.buildProducerProperties(new DefaultSslBundleRegistry()));
    }

    @Bean("publishMessageReactiveKafkaConsumerTemplate")
    public ReactiveKafkaConsumerTemplate<String, String> publishMessageReactiveKafkaConsumerTemplate() {
        return new ReactiveKafkaConsumerTemplate<>(publishMessageReceiverOptions());
    }

    @Bean("pendingMessageReactiveKafkaConsumerTemplate")
    public ReactiveKafkaConsumerTemplate<String, String> pendingMessageReactiveKafkaConsumerTemplate() {
        return new ReactiveKafkaConsumerTemplate<>(pendingMessageReceiverOptions());
    }

    @Bean
    public ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate() {
        return new ReactiveKafkaProducerTemplate<>(senderOptions());
    }
}
