package io.message.message.domain.search;

import io.message.message.domain.enums.MessageStatus;
import io.message.message.domain.search.base.Search;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "signal")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class SignalSearch extends Search {

    @Field(name = "status", type = FieldType.Text)
    private MessageStatus status;

    @Field(name = "content", type = FieldType.Text)
    private String content;
}
