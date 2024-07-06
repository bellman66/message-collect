package io.message.collect.domain.mapper;

import io.message.collect.domain.message.SignalMessage;
import io.message.collect.framework.web.data.request.MessageApiRequestGroup;
import io.message.collect.global.base.MapperExtension;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper extends MapperExtension {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target = "id", expression = GENERATE_ID_BY_UUID)
    @Mapping(target = "status", constant = "DRAFT")
    SignalMessage toMessage(MessageApiRequestGroup.CreateApiRequest request);

}
