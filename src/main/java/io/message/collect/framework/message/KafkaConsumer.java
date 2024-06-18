package io.message.collect.framework.message;

import io.message.collect.domain.message.SignalMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    @KafkaListener(topics = "${consumers.topics.publish-message}", groupId = "${consumers.group.name}")
    public void consumePublishMessage(ConsumerRecord<String, SignalMessage> record) {
        System.out.println("record : " + record);
    }

}
