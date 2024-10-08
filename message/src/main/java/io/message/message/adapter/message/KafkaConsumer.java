package io.message.message.adapter.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.message.message.MessageTopic;
import io.message.message.application.port.output.MessageOutput;
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

    private final ReactiveKafkaConsumerTemplate<String, ConsumerRecord<String, String>>
            publishMessageReactiveKafkaConsumerTemplate;
    private final ReactiveKafkaConsumerTemplate<String, ConsumerRecord<String, String>>
            pendingMessageReactiveKafkaConsumerTemplate;
    private final MessageOutput<PendingMessage> pendingMessageKafkaProducer;

    public Flux<?> publishMessageReactiveKafkaConsumerTemplateFlux() {
        return publishMessageReactiveKafkaConsumerTemplate
                .receiveAutoAck()
                .doOnNext(
                        consumerRecord ->
                                log.info(
                                        "[{} | {}] received key={}, value={}",
                                        consumerRecord.topic().toUpperCase(),
                                        consumerRecord.offset(),
                                        consumerRecord.key(),
                                        consumerRecord.value()))
                .flatMap(
                        consumerRecord ->
                                Mono.just(
                                        objectMapper.convertValue(
                                                consumerRecord.value(), SignalMessage.class)))
                .flatMap(
                        signalMessage -> {
                            Mono<SignalSearch> searchSaveMono = searchOutput.save(signalMessage);
                            Mono<MechanicalSignal> signalSaveMono =
                                    signalOutput.save(signalMessage);

                            return Mono.zip(searchSaveMono, signalSaveMono)
                                    .map(tuple -> (Object) tuple)
                                    .onErrorResume(
                                            throwable ->
                                                    pendingMessageKafkaProducer.save(
                                                            MessageTopic.PENDING,
                                                            PendingMessage.builder()
                                                                    .throwable(throwable)
                                                                    .id(signalMessage.getId())
                                                                    .build()));
                        })
                .doOnError(ex -> log.error(ex.getMessage()));
    }

    public Flux<?> pendingMessageReactiveKafkaConsumerTemplateFlux() {
        return pendingMessageReactiveKafkaConsumerTemplate
                .receiveAutoAck()
                .doOnNext(
                        consumerRecord ->
                                log.info(
                                        "[{} | {}] received key={}, value={}",
                                        consumerRecord.topic().toUpperCase(),
                                        consumerRecord.offset(),
                                        consumerRecord.key(),
                                        consumerRecord.value()))
                .flatMap(
                        consumerRecord ->
                                Mono.just(
                                        objectMapper.convertValue(
                                                consumerRecord.value(), SignalMessage.class)))
                .doOnNext(
                        signalMessage ->
                                log.info(
                                        "received pending message with id={}",
                                        signalMessage.getId()));
    }

    @Override
    public void run(ApplicationArguments args) {
        publishMessageReactiveKafkaConsumerTemplateFlux().subscribe();
        pendingMessageReactiveKafkaConsumerTemplateFlux().subscribe();
    }
}
