package io.message.collect.framework.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.message.collect.application.output.SignalOutput;
import io.message.collect.domain.message.SignalMessage;
import io.message.collect.domain.model.MechanicalSignal;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SignalOutput<MechanicalSignal> signalOutput;
    
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${consumers.topics.publish-message}", groupId = "${consumers.group.name}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void consumePublishMessage(ConsumerRecord<String, String> record) throws ExecutionException, InterruptedException, JsonProcessingException {
        SignalMessage signalMessage = objectMapper.readValue(record.value(), SignalMessage.class);
        signalOutput.save(signalMessage);
    }

}
