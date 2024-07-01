package io.message.collect.application.usecase;

import io.message.collect.domain.model.MechanicalSignal;

public interface SignalReadUseCase<T extends MechanicalSignal> {

    T findById(String id);

}
