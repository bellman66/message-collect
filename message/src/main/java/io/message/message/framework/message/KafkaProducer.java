package io.message.message.framework.message;

import io.message.message.application.port.output.MessageOutput;
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
    public Mono<T> save(MessageAble<T> messageAble) {
        T message = messageAble.toMessage();

        return reactiveKafkaProducerTemplate
                .send(TOPIC_PUBLISH_MESSAGE, message)
                .map(ignore -> message)
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Mono<T> pending(MessageAble<T> messageAble) {
        T message = messageAble.toMessage();

        return reactiveKafkaProducerTemplate
                .send(TOPIC_PENDING_MESSAGE, message)
                .map(ignore -> message)
                .doOnError(Throwable::printStackTrace);
    }
}
