package io.message.collect.framework.message;

import io.message.collect.application.output.MessageOutput;
import io.message.collect.domain.interfaces.MessageAble;
import io.message.collect.domain.model.Message;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer implements MessageOutput {

    @Value(value = "${producers.topics.publish-message}")
    private String TOPIC_PUBLISH_MESSAGE;

    private final KafkaTemplate<String, Message> messageKafkaTemplate;

    @Override
    public <T extends Message> String save(MessageAble<T> messageAble) throws ExecutionException, InterruptedException {
        CompletableFuture<SendResult<String, Message>> future = messageKafkaTemplate.send(TOPIC_PUBLISH_MESSAGE, messageAble.toMessage());
        SendResult<String, Message> sendResult = future.get();

        return sendResult.getProducerRecord().value().getId();
    }

}
