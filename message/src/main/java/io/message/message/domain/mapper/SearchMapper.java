package io.message.message.domain.mapper;

import io.message.message.domain.message.SignalMessage;
import io.message.message.domain.search.SignalSearch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SearchMapper {

    SearchMapper INSTANCE = Mappers.getMapper(SearchMapper.class);

    SignalSearch toSearch(SignalMessage message);
}
