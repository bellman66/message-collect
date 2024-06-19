package io.message.collect.domain.message.base;

import io.message.collect.domain.interfaces.MessageAble;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public abstract class DefaultMessage<T> implements MessageAble<T> {

    @PrimaryKey
    private String id;

}
