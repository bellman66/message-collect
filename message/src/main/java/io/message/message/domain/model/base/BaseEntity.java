package io.message.message.domain.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

@Getter
@Setter
@SuperBuilder
public abstract class BaseEntity {

  @PrimaryKey private String id;
}
