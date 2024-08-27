package io.message.message.domain.interfaces;

import io.message.message.domain.search.base.Search;

public interface SearchAble<T extends Search> {

  T toSearch();
}
