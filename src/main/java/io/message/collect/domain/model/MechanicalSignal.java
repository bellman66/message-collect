package io.message.collect.domain.model;

import io.message.collect.domain.enums.MessageStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Getter
@Setter
@SuperBuilder
public class MechanicalSignal {

    @PrimaryKey
    private String id;

    private MessageStatus status;

    private String content;

}
