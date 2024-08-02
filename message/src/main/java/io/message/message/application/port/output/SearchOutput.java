package io.message.message.application.port.output;

import io.message.message.domain.interfaces.SearchAble;
import io.message.message.domain.search.SignalSearch;
import io.message.message.domain.search.base.Search;
import java.util.concurrent.ExecutionException;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SearchOutput<T extends Search> {

    Mono<T> save(SearchAble<T> search) throws ExecutionException, InterruptedException;

    Flux<SearchHit<SignalSearch>> searchByQuery(Query query);
}
