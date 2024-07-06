package io.message.collect.domain.mapper;

import io.message.collect.domain.message.SignalMessage;
import io.message.collect.domain.model.MechanicalSignal;
import io.message.collect.domain.search.SignalSearch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SignalMapper {

    SignalMapper INSTANCE = Mappers.getMapper(SignalMapper.class);

    MechanicalSignal toSignal(SignalMessage message);

}
