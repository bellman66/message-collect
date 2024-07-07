package io.message.collect.application.port.input;

import io.message.collect.domain.search.base.Search;

public interface SignalReadUseCase<T extends Search> {

    T searchById(String id);

}