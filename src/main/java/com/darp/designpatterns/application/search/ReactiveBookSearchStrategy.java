package com.darp.designpatterns.application.search;

import com.darp.designpatterns.domain.models.Book;
import reactor.core.publisher.Flux;

public interface ReactiveBookSearchStrategy {
  Flux<Book> search(String value);
}
