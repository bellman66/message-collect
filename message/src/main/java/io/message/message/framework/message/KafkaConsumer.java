package io.message.message.framework.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.message.message.application.port.output.SearchOutput;
import io.message.message.application.port.output.SignalOutput;
import io.message.message.domain.message.PendingMessage;
import io.message.message.domain.message.SignalMessage;
import io.message.message.domain.model.MechanicalSignal;
import io.message.message.domain.search.SignalSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer implements ApplicationRunner {

    private final ObjectMapper objectMapper;
    private final SignalOutput<MechanicalSignal> signalOutput;
    private final SearchOutput<SignalSearch> searchOutput;
    private final KafkaProducer<PendingMessage> pendingMessageKafkaProducer;

    private final ReactiveKafkaConsumerTemplate<String, ConsumerRecord<String, String>> recordReactiveKafkaConsumerTemplate;

    public Flux<?> consumePublishMessage() {
        return recordReactiveKafkaConsumerTemplate
                .receiveAutoAck()
                .flatMap(consumerRecord -> Mono.fromCallable(() -> {
                            try {
                                ConsumerRecord<String, String> recordValue = consumerRecord.value();
                                return objectMapper.readValue(recordValue.value(), SignalMessage.class);
                            } catch (JsonProcessingException e) {
                                return Mono.error(e);
                            }
                        })
                )
                .flatMap(signalMessage -> {
                    try {
                        SignalMessage signalMessage1 = (SignalMessage) signalMessage;
                        Mono<SignalSearch> searchSaveMono = searchOutput.save(signalMessage1);
                        Mono<MechanicalSignal> signalSaveMono = signalOutput.save(signalMessage1);

                        return Mono.zip(searchSaveMono, signalSaveMono);
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                });
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        consumePublishMessage().subscribe();
    }
}
