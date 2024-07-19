package io.message.account.application.port.input;

import io.message.account.domain.interfaces.EntityAble;

public interface AccountSignUpUseCase<T> {

    T save(EntityAble<T> saveAble);
}
