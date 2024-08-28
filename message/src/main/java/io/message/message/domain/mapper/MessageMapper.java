package io.message.message.domain.mapper;

import io.message.message.domain.message.PendingMessage;
import io.message.message.domain.message.SignalMessage;
import io.message.message.framework.web.data.request.MessageApiRequestGroup;
import io.message.message.global.base.MapperExtension;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper extends MapperExtension {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target = "id", expression = GENERATE_ID_BY_UUID)
    SignalMessage toMessage(MessageApiRequestGroup.CreateApiRequest request);

    PendingMessage toPendingMessage(SignalMessage message, Throwable throwable);
}
