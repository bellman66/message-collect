package io.message.message.domain.message;

import io.message.message.domain.message.base.StatusTrackingMessage;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PendingMessage extends StatusTrackingMessage<PendingMessage> {

    private Exception exception;

    @Override
    public PendingMessage toMessage() {
        return this;
    }
}
