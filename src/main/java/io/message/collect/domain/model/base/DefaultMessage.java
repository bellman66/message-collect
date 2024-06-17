package io.message.collect.domain.model.base;

import io.message.collect.domain.interfaces.MessageAble;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

@Getter
@SuperBuilder
public abstract class DefaultMessage<T> implements MessageAble<T> {

    @PrimaryKey
    private final String id;

}
