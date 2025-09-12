package com.darp.designpatterns.domain.ports;

import com.darp.designpatterns.domain.models.Book;
import reactor.core.publisher.Flux;

public interface BookRepositoryPort {
  Flux<Book> findAll();

  Flux<Book> findByTitleContainsIgnoreCase(String titlePart);

  Flux<Book> findByAuthorContainsIgnoreCase(String authorPart);

  Flux<Book> saveAll(Iterable<Book> books);
}
