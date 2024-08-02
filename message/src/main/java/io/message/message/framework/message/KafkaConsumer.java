package io.message.message.framework.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.message.message.application.port.output.SearchOutput;
import io.message.message.application.port.output.SignalOutput;
import io.message.message.domain.mapper.MessageMapper;
import io.message.message.domain.message.PendingMessage;
import io.message.message.domain.message.SignalMessage;
import io.message.message.domain.model.MechanicalSignal;
import io.message.message.domain.search.SignalSearch;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final SignalOutput<MechanicalSignal> signalOutput;
    private final SearchOutput<SignalSearch> searchOutput;
    private final KafkaProducer<PendingMessage> pendingMessageKafkaProducer;

    @KafkaListener(topics = "${consumers.topics.publish-message}", groupId = "${consumers.group.name}")
    @Transactional
    public void consumePublishMessage(ConsumerRecord<String, String> record) throws JsonProcessingException, ExecutionException, InterruptedException {
        SignalMessage signalMessage = objectMapper.readValue(record.value(), SignalMessage.class);

        Mono<SignalSearch> searchSaveMono = searchOutput.save(signalMessage);
        Mono<MechanicalSignal> signalSaveMono = signalOutput.save(signalMessage);

        Mono.when(searchSaveMono, signalSaveMono)
                .doOnError(throwable -> pendingMessageKafkaProducer.pending(MessageMapper.INSTANCE.toPendingMessage(signalMessage, throwable)))
                .subscribe();
    }
}
