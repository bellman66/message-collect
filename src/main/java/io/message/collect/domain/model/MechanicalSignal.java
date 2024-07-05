package io.message.collect.domain.model;

import io.message.collect.domain.enums.MessageStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "mechanical_signal")
@Table
@Getter
@Setter
@SuperBuilder
public class MechanicalSignal {

    @PrimaryKey
    @Field(name = "content", type = FieldType.Text)
    private String id;

    @Field(name = "status", type = FieldType.Text)
    private MessageStatus status;

    @Field(name = "status", type = FieldType.Text)
    private String content;

}
