package io.message.collect.application.usecase;

import io.message.collect.domain.interfaces.EntityAble;

public interface InquiryUseCase<T> {

    T save(EntityAble<T> saveAble);

}
