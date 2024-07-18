package io.message.message.framework.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.message.message.application.port.output.MessageOutput;
import io.message.message.application.port.output.SearchOutput;
import io.message.message.application.port.output.SignalOutput;
import io.message.message.domain.message.SignalMessage;
import io.message.message.domain.model.MechanicalSignal;
import io.message.message.domain.search.SignalSearch;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    private final MessageOutput messageOutput;
    private final SignalOutput<MechanicalSignal> signalOutput;
    private final SearchOutput<SignalSearch> searchOutput;

    @KafkaListener(topics = "${consumers.topics.publish-message}", groupId = "${consumers.group.name}")
    @Transactional
    public void consumePublishMessage(ConsumerRecord<String, String> record) throws ExecutionException, InterruptedException, JsonProcessingException {
        SignalMessage signalMessage = objectMapper.readValue(record.value(), SignalMessage.class);

        // Save Search Database
        searchOutput.save(signalMessage);

        // Save Main Database
        signalOutput.save(signalMessage);
    }

}
