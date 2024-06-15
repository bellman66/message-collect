package io.message.collect.application.output;

import io.message.collect.domain.interfaces.EntityAble;

public interface AccountOutput<T> {

    T save(EntityAble<T> saveAble);

}
