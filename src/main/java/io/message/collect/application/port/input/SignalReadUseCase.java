package io.message.collect.application.port.input;

import io.message.collect.domain.search.base.Search;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.List;

public interface SignalReadUseCase<T extends Search> {

    T searchById(String id);

    List<SearchHit<T>> searchGroupByQuery(String query);
    
}
