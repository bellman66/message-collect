package io.message.message.domain.search.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class Search {

  @Id
  @Field(name = "id", type = FieldType.Text)
  protected String id;
}
