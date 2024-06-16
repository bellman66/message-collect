package io.message.collect.domain.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @PrimaryKey
    private String id;

    private String content;

    public static Message create(String content) {
        return new Message(UUID.randomUUID().toString(), content);
    }

}
