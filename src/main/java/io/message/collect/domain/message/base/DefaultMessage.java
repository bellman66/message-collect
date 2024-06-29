package io.message.collect.domain.message.base;

import io.message.collect.domain.interfaces.MessageAble;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public abstract class DefaultMessage<T> implements MessageAble<T> {

    @Id
    @Field(name = "id", type = FieldType.Text)
    @PrimaryKey
    private String id;

}
