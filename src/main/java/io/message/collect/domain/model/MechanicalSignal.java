package io.message.collect.domain.model;

import io.message.collect.domain.enums.MessageStatus;
import io.message.collect.domain.model.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Getter
@Setter
@SuperBuilder
public class MechanicalSignal extends BaseEntity {

    private MessageStatus status;

    private String content;

}
