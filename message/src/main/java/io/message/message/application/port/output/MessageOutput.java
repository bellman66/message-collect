package io.message.message.application.port.output;

import io.message.message.domain.interfaces.MessageAble;
import io.message.message.domain.message.SignalMessage;

import java.util.concurrent.ExecutionException;

public interface MessageOutput {

    <T extends SignalMessage> String save(MessageAble<T> messageAble) throws ExecutionException, InterruptedException;

}
