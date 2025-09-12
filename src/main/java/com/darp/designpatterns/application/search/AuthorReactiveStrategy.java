package com.darp.designpatterns.application.search;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.ports.BookRepositoryPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class AuthorReactiveStrategy implements ReactiveBookSearchStrategy {
  private final BookRepositoryPort repositoryPort;

  @Override
  public Flux<Book> search(String value) {
    return repositoryPort.findByAuthorContainsIgnoreCase(value == null ? "" : value);
  }
}
