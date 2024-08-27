package io.message.message.application.port.input;

import io.message.message.domain.search.base.Search;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import reactor.core.publisher.Flux;

public interface SignalReadUseCase<T extends Search> {

  Flux<SearchHit<T>> searchGroupByQuery(Query query);
}
