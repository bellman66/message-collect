package io.message.message.framework.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.message.message.application.port.output.SearchOutput;
import io.message.message.application.port.output.SignalOutput;
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

    private final ReactiveKafkaConsumerTemplate<String, ConsumerRecord<String, String>> recordReactiveKafkaConsumerTemplate;

    public Flux<?> consumePublishMessage() {
        return recordReactiveKafkaConsumerTemplate
                .receiveAutoAck()
                .doOnNext(consumerRecord -> log.info("received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                )
                .flatMap(consumerRecord -> Mono.just(objectMapper.convertValue(consumerRecord.value(), SignalMessage.class)))
                .flatMap(signalMessage -> {
                    try {
                        Mono<SignalSearch> searchSaveMono = searchOutput.save(signalMessage);
                        Mono<MechanicalSignal> signalSaveMono = signalOutput.save(signalMessage);

                        return Mono.zip(searchSaveMono, signalSaveMono);
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                })
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public void run(ApplicationArguments args) {
        consumePublishMessage().subscribe();
    }
}
