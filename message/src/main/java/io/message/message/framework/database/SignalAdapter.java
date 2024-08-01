package io.message.message.framework.database;

import io.message.message.application.port.output.SearchOutput;
import io.message.message.application.port.output.SignalOutput;
import io.message.message.domain.interfaces.EntityAble;
import io.message.message.domain.interfaces.SearchAble;
import io.message.message.domain.model.MechanicalSignal;
import io.message.message.domain.search.SignalSearch;
import io.message.message.framework.database.jpa.MechanicalSignalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SignalAdapter implements SignalOutput<MechanicalSignal>, SearchOutput<SignalSearch> {

    private final MechanicalSignalRepository mechanicalsignalRepository;

    private final ReactiveElasticsearchOperations reactiveElasticsearchOperations;

    @Override
    @Transactional
    public Mono<MechanicalSignal> save(EntityAble<MechanicalSignal> entityAble) {
        return mechanicalsignalRepository.save(entityAble.toEntity());
    }

    @Override
    public Mono<MechanicalSignal> findById(String id) {
        return reactiveElasticsearchOperations.get(id, MechanicalSignal.class);
    }

    @Override
    @Transactional
    public Mono<SignalSearch> save(SearchAble<SignalSearch> search) {
        return reactiveElasticsearchOperations.save(search.toSearch());
    }

    @Override
    public Mono<SignalSearch> searchById(String id) {
        return reactiveElasticsearchOperations.get(id, SignalSearch.class);
    }

    @Override
    public Flux<SearchHit<SignalSearch>> searchByQuery(Query query) {
        return reactiveElasticsearchOperations.search(query, SignalSearch.class);
    }
}
