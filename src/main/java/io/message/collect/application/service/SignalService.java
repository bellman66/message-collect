package io.message.collect.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.message.collect.application.port.input.SignalReadUseCase;
import io.message.collect.application.port.input.SignalStoreUseCase;
import io.message.collect.application.port.output.SearchOutput;
import io.message.collect.application.port.output.SignalOutput;
import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.model.MechanicalSignal;
import io.message.collect.domain.search.SignalSearch;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SignalService implements SignalStoreUseCase<MechanicalSignal>, SignalReadUseCase<SignalSearch> {

    private final SignalOutput<MechanicalSignal> signalOutput;
    private final SearchOutput<SignalSearch> searchOutput;
    private final ObjectMapper objectMapper;

    @Override
    public MechanicalSignal save(EntityAble<MechanicalSignal> saveAble) throws ExecutionException, InterruptedException {
        return signalOutput.save(saveAble);
    }

    @Override
    public SignalSearch searchById(String id) {
        return searchOutput.searchById(id).orElseThrow();
    }

    @Override
    public List<SearchHit<SignalSearch>> searchGroupByQuery(Query query) {
        return searchOutput.searchByQuery(query);
    }

}
