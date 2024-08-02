package io.message.message.application.service;

import io.message.message.application.port.input.SignalReadUseCase;
import io.message.message.application.port.output.SearchOutput;
import io.message.message.domain.search.SignalSearch;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SignalService implements SignalReadUseCase<SignalSearch> {

    private final SearchOutput<SignalSearch> searchOutput;

    @Override
    public Flux<SearchHit<SignalSearch>> searchGroupByQuery(Query query) {
        return searchOutput.searchByQuery(query);
    }
}
