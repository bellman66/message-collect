package io.message.message.application.service;

import io.message.message.application.port.input.SignalReadUseCase;
import io.message.message.application.port.input.SignalStoreUseCase;
import io.message.message.application.port.output.SearchOutput;
import io.message.message.application.port.output.SignalOutput;
import io.message.message.domain.interfaces.EntityAble;
import io.message.message.domain.model.MechanicalSignal;
import io.message.message.domain.search.SignalSearch;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SignalService implements SignalStoreUseCase<MechanicalSignal>, SignalReadUseCase<SignalSearch> {

    private final SignalOutput<MechanicalSignal> signalOutput;
    private final SearchOutput<SignalSearch> searchOutput;

    @Override
    public Mono<MechanicalSignal> save(EntityAble<MechanicalSignal> saveAble) throws ExecutionException, InterruptedException {
        return signalOutput.save(saveAble);
    }

    @Override
    public Flux<SearchHit<SignalSearch>> searchGroupByQuery(Query query) {
        return searchOutput.searchByQuery(query);
    }
}
