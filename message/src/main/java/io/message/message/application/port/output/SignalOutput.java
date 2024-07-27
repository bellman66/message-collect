package io.message.message.application.port.output;

import io.message.message.domain.interfaces.EntityAble;
import io.message.message.domain.model.MechanicalSignal;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import reactor.core.publisher.Mono;

public interface SignalOutput<T extends MechanicalSignal> {

    Mono<T> save(EntityAble<T> messageAble) throws ExecutionException, InterruptedException;

    Optional<T> findById(String id);

}
