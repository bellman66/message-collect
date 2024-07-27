package io.message.message.application.port.input;

import io.message.message.domain.interfaces.EntityAble;
import io.message.message.domain.model.MechanicalSignal;
import java.util.concurrent.ExecutionException;
import reactor.core.publisher.Mono;

public interface SignalStoreUseCase<T extends MechanicalSignal> {

    Mono<T> save(EntityAble<T> saveAble) throws ExecutionException, InterruptedException;

}
