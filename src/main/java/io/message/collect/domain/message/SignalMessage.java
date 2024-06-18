package io.message.collect.domain.message;

import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.message.base.StatusTrackingMessage;
import io.message.collect.domain.model.MechanicalSignal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SignalMessage extends StatusTrackingMessage<SignalMessage> implements EntityAble<MechanicalSignal> {

    private String content;

    @Override
    public SignalMessage toMessage() {
        return this;
    }

    @Override
    public MechanicalSignal toEntity() {
        return null;
    }
}
