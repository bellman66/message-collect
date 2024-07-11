package io.message.collect.application.port.output;

import io.message.collect.domain.interfaces.SearchAble;
import io.message.collect.domain.search.SignalSearch;
import io.message.collect.domain.search.base.Search;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

public interface SearchOutput<T extends Search> {

    T save(SearchAble<T> search) throws ExecutionException, InterruptedException;

    Optional<T> searchById(String id);

    SearchHits<SignalSearch> searchByQuery(Query query);

}
