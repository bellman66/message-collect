package io.message.collect.application.input;

import io.message.collect.application.output.SignalOutput;
import io.message.collect.application.usecase.SignalStoreUseCase;
import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.model.MechanicalSignal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SignalInput implements SignalStoreUseCase<MechanicalSignal> {

    private final SignalOutput<MechanicalSignal> signalOutput;

    @Override
    public MechanicalSignal save(EntityAble<MechanicalSignal> saveAble) throws ExecutionException, InterruptedException {
        return signalOutput.save(saveAble);
    }

}
