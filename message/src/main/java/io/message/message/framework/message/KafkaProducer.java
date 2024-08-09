package io.message.message.framework.message;

import io.message.message.application.port.output.MessageOutput;
import io.message.message.domain.interfaces.MessageAble;
import io.message.message.domain.message.SignalMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderRecord;

import java.util.UUID;

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
        SenderRecord<String, T, Class<SignalMessage>> senderRecord = SenderRecord.create(TOPIC_PUBLISH_MESSAGE, null, null, UUID.randomUUID().toString(), messageAble.toMessage(), SignalMessage.class);

        return reactiveKafkaProducerTemplate
                .sendTransactionally(senderRecord)
                .map(sendResult -> messageAble.toMessage())
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public void pending(MessageAble<T> messageAble) {
        reactiveKafkaProducerTemplate
                .send(TOPIC_PENDING_MESSAGE, messageAble.toMessage())
                .doOnError(Throwable::printStackTrace)
                .subscribe();
    }
}
