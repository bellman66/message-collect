package io.message.message.framework.message;

import io.message.message.application.port.output.MessageOutput;
import io.message.message.domain.interfaces.MessageAble;
import io.message.message.domain.message.SignalMessage;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KafkaProducer<T extends SignalMessage> implements MessageOutput<T> {

    @Value(value = "${producers.topics.publish-message}")
    private String TOPIC_PUBLISH_MESSAGE;

    private final KafkaTemplate<String, T> messageKafkaTemplate;

    @Override
    public Mono<T> save(MessageAble<T> messageAble) {
        CompletableFuture<SendResult<String, T>> future = messageKafkaTemplate.send(TOPIC_PUBLISH_MESSAGE, messageAble.toMessage());

        return Mono.fromFuture(future)
                .map(sendResult -> sendResult.getProducerRecord().value())
                .doOnError(Throwable::printStackTrace);
    }
}
