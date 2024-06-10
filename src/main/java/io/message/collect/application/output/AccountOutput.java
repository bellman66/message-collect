package io.message.collect.application.output;

import io.message.collect.domain.interfaces.SaveAble;

public interface AccountOutput<T> {
    
    T save(SaveAble<T> saveAble);

}
