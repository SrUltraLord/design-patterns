package com.darp.designpatterns.application.search;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.ports.BookRepositoryPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class TitleReactiveStrategy implements ReactiveBookSearchStrategy {
  private final BookRepositoryPort repositoryPort;

  @Override
  public Flux<Book> search(String value) {
    return repositoryPort.findByTitleContainsIgnoreCase(value == null ? "" : value);
  }
}
