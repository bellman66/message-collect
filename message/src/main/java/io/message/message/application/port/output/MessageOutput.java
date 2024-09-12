package io.message.message.application.port.output;

import io.message.message.MessageTopic;
import io.message.message.domain.interfaces.MessageAble;
import reactor.core.publisher.Mono;

public interface MessageOutput<T extends MessageAble<?>> {

    Mono<T> save(MessageTopic status, MessageAble<T> messageAble);
}
