package io.message.account.application.port.output;

import io.message.account.domain.interfaces.EntityAble;

public interface AccountOutput<T> {

    T save(EntityAble<T> saveAble);
}
