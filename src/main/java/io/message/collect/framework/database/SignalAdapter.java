package io.message.collect.framework.database;

import io.message.collect.application.port.output.SearchOutput;
import io.message.collect.application.port.output.SignalOutput;
import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.interfaces.SearchAble;
import io.message.collect.domain.model.MechanicalSignal;
import io.message.collect.domain.search.SignalSearch;
import io.message.collect.framework.database.jpa.MechanicalSignalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SignalAdapter implements SignalOutput<MechanicalSignal>, SearchOutput<SignalSearch> {

    private final MechanicalSignalRepository mechanicalsignalRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    @Transactional
    public MechanicalSignal save(EntityAble<MechanicalSignal> entityAble) throws ExecutionException, InterruptedException {
        return mechanicalsignalRepository.save(entityAble.toEntity());
    }

    @Override
    public Optional<MechanicalSignal> findById(String id) {
        return Optional.ofNullable(elasticsearchOperations.get(id, MechanicalSignal.class));
    }

    @Override
    @Transactional
    public SignalSearch save(SearchAble<SignalSearch> search) throws ExecutionException, InterruptedException {
        return elasticsearchOperations.save(search.toSearch());
    }

    @Override
    public Optional<SignalSearch> searchById(String id) {
        return Optional.ofNullable(elasticsearchOperations.get(id, SignalSearch.class));
    }

    @Override
    public List<SearchHit<SignalSearch>> searchByQuery(Query query) {
        SearchHits<SignalSearch> searchHits = elasticsearchOperations.search(query, SignalSearch.class);

        return searchHits.hasSearchHits() ? searchHits.getSearchHits() : Collections.emptyList();
    }

}
