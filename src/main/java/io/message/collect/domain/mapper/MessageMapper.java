package io.message.collect.domain.mapper;

import io.message.collect.domain.model.Message;
import io.message.collect.framework.web.data.request.MessageApiRequestGroup;
import io.message.collect.global.base.MapperExtension;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UUID.class)
public interface MessageMapper extends MapperExtension {

    @Mapping(target = "id", expression = GENERATE_ID_BY_UUID)
    Message toEntity(MessageApiRequestGroup.CreateApiRequest request);

}
