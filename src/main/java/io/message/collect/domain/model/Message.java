package io.message.collect.domain.model;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {

    @PrimaryKey
    private String id;

    private String content;

    public static Message create(String content) {
        return new Message(UUID.randomUUID().toString(), content);
    }

}
