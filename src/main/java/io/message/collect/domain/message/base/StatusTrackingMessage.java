package io.message.collect.domain.message.base;

import io.message.collect.domain.enums.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class StatusTrackingMessage<T> extends DefaultMessage<T> {

    @Field(name = "status", type = FieldType.Text)
    private MessageStatus status;

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
