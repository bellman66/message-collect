package io.message.message.domain.message;

import io.message.message.domain.interfaces.EntityAble;
import io.message.message.domain.interfaces.SearchAble;
import io.message.message.domain.mapper.SearchMapper;
import io.message.message.domain.mapper.SignalMapper;
import io.message.message.domain.message.base.StatusTrackingMessage;
import io.message.message.domain.model.MechanicalSignal;
import io.message.message.domain.search.SignalSearch;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SignalMessage extends StatusTrackingMessage<SignalMessage> implements EntityAble<MechanicalSignal>, SearchAble<SignalSearch> {

    private String content;

    @Override
    public SignalMessage toMessage() {
        return this;
    }

    @Override
    public MechanicalSignal toEntity() {
        return SignalMapper.INSTANCE.toSignal(this);
    }

    @Override
    public SignalSearch toSearch() {
        return SearchMapper.INSTANCE.toSearch(this);
    }

}
