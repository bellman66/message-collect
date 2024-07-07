package io.message.collect.application.port.output;

import io.message.collect.domain.interfaces.MessageAble;
import io.message.collect.domain.message.SignalMessage;
import java.util.concurrent.ExecutionException;

public interface MessageOutput {

    <T extends SignalMessage> String save(MessageAble<T> messageAble) throws ExecutionException, InterruptedException;

}
