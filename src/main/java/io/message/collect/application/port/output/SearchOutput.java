package io.message.collect.application.port.output;

import io.message.collect.domain.interfaces.SearchAble;
import io.message.collect.domain.search.SignalSearch;
import io.message.collect.domain.search.base.Search;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface SearchOutput<T extends Search> {

    T save(SearchAble<T> search) throws ExecutionException, InterruptedException;

    Optional<T> searchById(String id);

    List<SearchHit<SignalSearch>> searchByQuery(Query query);

}
