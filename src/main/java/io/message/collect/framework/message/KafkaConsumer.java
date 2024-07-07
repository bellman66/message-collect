package io.message.collect.framework.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.message.collect.application.port.output.MessageOutput;
import io.message.collect.application.port.output.SearchOutput;
import io.message.collect.application.port.output.SignalOutput;
import io.message.collect.domain.message.SignalMessage;
import io.message.collect.domain.model.MechanicalSignal;
import io.message.collect.domain.search.SignalSearch;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
