package io.message.message.config;

import java.util.List;
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

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Value("${consumers.topics.publish-message}")
    private String publishMessageTopic;

    @Value("${consumers.topics.pending-message}")
    private String pendingMessageTopic;

    @Bean("publishMessageReceiverOptions")
    public ReceiverOptions<String, String> publishMessageReceiverOptions() {
        ReceiverOptions<String, String> receiverOptions =
                ReceiverOptions.create(
                        kafkaProperties.buildConsumerProperties(new DefaultSslBundleRegistry()));
        return receiverOptions.subscription(List.of(publishMessageTopic));
    }

    @Bean("pendingMessageReceiverOptions")
    public ReceiverOptions<String, String> pendingMessageReceiverOptions() {
        ReceiverOptions<String, String> receiverOptions =
                ReceiverOptions.create(
                        kafkaProperties.buildConsumerProperties(new DefaultSslBundleRegistry()));
        return receiverOptions.subscription(List.of(pendingMessageTopic));
    }

    @Bean
    public SenderOptions<String, String> senderOptions() {
        return SenderOptions.create(
                kafkaProperties.buildProducerProperties(new DefaultSslBundleRegistry()));
    }

    @Bean("publishMessageReactiveKafkaConsumerTemplate")
    public ReactiveKafkaConsumerTemplate publishMessageReactiveKafkaConsumerTemplate() {
        return new ReactiveKafkaConsumerTemplate<>(publishMessageReceiverOptions());
    }

    @Bean("pendingMessageReactiveKafkaConsumerTemplate")
    public ReactiveKafkaConsumerTemplate pendingMessageReactiveKafkaConsumerTemplate() {
        return new ReactiveKafkaConsumerTemplate<>(pendingMessageReceiverOptions());
    }

    @Bean
    public ReactiveKafkaProducerTemplate reactiveKafkaProducerTemplate() {
        return new ReactiveKafkaProducerTemplate<>(senderOptions());
    }
}
