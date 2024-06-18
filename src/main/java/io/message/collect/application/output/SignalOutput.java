package io.message.collect.application.output;

import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.model.MechanicalSignal;

import java.util.concurrent.ExecutionException;

public interface SignalOutput<T extends MechanicalSignal> {

    T save(EntityAble<T> messageAble) throws ExecutionException, InterruptedException;

}
