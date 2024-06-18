package io.message.collect.domain.mapper;

import io.message.collect.domain.message.SignalMessage;
import io.message.collect.framework.web.data.request.MessageApiRequestGroup;
import io.message.collect.global.base.MapperExtension;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UUID.class)
public interface MessageMapper extends MapperExtension {

    @Mapping(target = "id", expression = GENERATE_ID_BY_UUID)
    @Mapping(target = "status", constant = "DRAFT")
    SignalMessage toEntity(MessageApiRequestGroup.CreateApiRequest request);

}
