package io.message.collect.application.port.input;

import io.message.collect.domain.interfaces.EntityAble;

public interface AccountSignUpUseCase<T> {

    T save(EntityAble<T> saveAble);

}
