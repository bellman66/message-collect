package io.message.collect.domain.interfaces;

import io.message.collect.domain.search.base.Search;

public interface SearchAble<T extends Search> {

    T toSearch();

}
