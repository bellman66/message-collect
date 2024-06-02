package io.message.collect.application.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Getter
@Setter
@AllArgsConstructor
public class Person {

    @PrimaryKey
    private final String id;

    private final String name;

    private final int age;

}
