package io.message.message.domain.message;

import io.message.message.domain.message.base.DefaultMessage;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PendingMessage extends DefaultMessage<PendingMessage> {

  private Throwable throwable;

  @Override
  public PendingMessage toMessage() {
    return this;
  }
}
