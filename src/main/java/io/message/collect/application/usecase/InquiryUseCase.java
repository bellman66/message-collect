package io.message.collect.application.usecase;

import io.message.collect.domain.interfaces.SaveAble;

public interface InquiryUseCase<T> {

    T save(SaveAble<T> saveAble);

}
