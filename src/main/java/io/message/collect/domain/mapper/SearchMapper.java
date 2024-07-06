package io.message.collect.domain.mapper;

import io.message.collect.domain.message.SignalMessage;
import io.message.collect.domain.search.SignalSearch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SearchMapper {

    SearchMapper INSTANCE = Mappers.getMapper(SearchMapper.class);

    SignalSearch toSearch(SignalMessage message);

}
