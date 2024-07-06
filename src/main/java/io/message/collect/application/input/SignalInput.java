package io.message.collect.application.input;

import io.message.collect.application.output.SearchOutput;
import io.message.collect.application.output.SignalOutput;
import io.message.collect.application.usecase.SignalReadUseCase;
import io.message.collect.application.usecase.SignalStoreUseCase;
import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.model.MechanicalSignal;
import io.message.collect.domain.search.SignalSearch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SignalInput implements SignalStoreUseCase<MechanicalSignal>, SignalReadUseCase<SignalSearch> {

    private final SignalOutput<MechanicalSignal> signalOutput;
    private final SearchOutput<SignalSearch> searchOutput;

    @Override
    public MechanicalSignal save(EntityAble<MechanicalSignal> saveAble) throws ExecutionException, InterruptedException {
        return signalOutput.save(saveAble);
    }

    @Override
    public SignalSearch searchById(String id) {
        return searchOutput.searchById(id).orElseThrow();
    }

}
