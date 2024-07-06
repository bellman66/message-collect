package io.message.collect.domain.message;

import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.interfaces.SearchAble;
import io.message.collect.domain.mapper.SearchMapper;
import io.message.collect.domain.mapper.SignalMapper;
import io.message.collect.domain.message.base.StatusTrackingMessage;
import io.message.collect.domain.model.MechanicalSignal;
import io.message.collect.domain.search.SignalSearch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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
