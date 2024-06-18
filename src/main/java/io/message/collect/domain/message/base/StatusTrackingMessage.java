package io.message.collect.domain.message.base;

import io.message.collect.domain.enums.MessageStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class StatusTrackingMessage<T> extends DefaultMessage<T> {

    private final MessageStatus status;

}
