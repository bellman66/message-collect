package io.message.collect.framework.database;

import io.message.collect.application.output.SignalOutput;
import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.model.MechanicalSignal;
import io.message.collect.framework.database.repository.SignalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SignalAdapter implements SignalOutput<MechanicalSignal> {

    private final SignalRepository signalRepository;

    @Override
    public MechanicalSignal save(EntityAble<MechanicalSignal> entityAble) throws ExecutionException, InterruptedException {
        return signalRepository.save(entityAble.toEntity());
    }
    
}
