package io.message.collect.application.port.input;

import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.model.MechanicalSignal;
import java.util.concurrent.ExecutionException;

public interface SignalStoreUseCase<T extends MechanicalSignal> {

    T save(EntityAble<T> saveAble) throws ExecutionException, InterruptedException;

}
