package io.message.collect.domain.model;

import io.message.collect.domain.model.base.StatusTrackingMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SignalMessage extends StatusTrackingMessage<SignalMessage> {

    private String content;

    @Override
    public SignalMessage toMessage() {
        return this;
    }

}
