package io.message.message.adapter.message;

import io.message.message.MessageTopic;
import io.message.message.application.port.output.MessageOutput;
import io.message.message.domain.interfaces.MessageAble;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KafkaProducer<T extends MessageAble<?>> implements MessageOutput<T> {

    private final ReactiveKafkaProducerTemplate<String, T> reactiveKafkaProducerTemplate;

    @Override
    public Mono<T> save(MessageTopic topic, MessageAble<T> messageAble) {
        T message = messageAble.toMessage();

        return reactiveKafkaProducerTemplate
                .send(topic.name(), message)
                .map(ignore -> message)
                .doOnError(Throwable::printStackTrace);
    }
}
