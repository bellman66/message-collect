package io.message.message.adapter.message;

import io.message.message.application.port.output.MessageOutput;
import io.message.message.domain.enums.MessageStatus;
import io.message.message.domain.interfaces.MessageAble;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KafkaProducer<T extends MessageAble<?>> implements MessageOutput<T> {

    @Value(value = "${producers.topics.publish-message}")
    private String TOPIC_PUBLISH_MESSAGE;

    @Value(value = "${producers.topics.pending-message}")
    private String TOPIC_PENDING_MESSAGE;

    private final ReactiveKafkaProducerTemplate<String, T> reactiveKafkaProducerTemplate;

    @Override
    public Mono<T> save(MessageStatus status, MessageAble<T> messageAble) {
        T message = messageAble.toMessage();

        return reactiveKafkaProducerTemplate
                .send(convertToTopic(status), message)
                .map(ignore -> message)
                .doOnError(Throwable::printStackTrace);
    }

    private String convertToTopic(MessageStatus status) {
        switch (status) {
            case DRAFT:
                return TOPIC_PUBLISH_MESSAGE;
            case PENDING:
                return TOPIC_PENDING_MESSAGE;
            default:
                throw new IllegalArgumentException("Invalid message status");
        }
    }
}
