package io.message.message.domain.mapper;

import io.message.message.domain.message.SignalMessage;
import io.message.message.domain.model.MechanicalSignal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SignalMapper {

    SignalMapper INSTANCE = Mappers.getMapper(SignalMapper.class);

    MechanicalSignal toSignal(SignalMessage message);
}
