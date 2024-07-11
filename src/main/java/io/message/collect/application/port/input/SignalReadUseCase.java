package io.message.collect.application.port.input;

import io.message.collect.domain.search.base.Search;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

public interface SignalReadUseCase<T extends Search> {

    T searchById(String id);

    SearchHits<T> searchGroupByQuery(Query query);

}
