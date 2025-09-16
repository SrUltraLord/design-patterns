package com.darp.designpatterns.domain.ports;

import com.darp.designpatterns.domain.models.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepositoryPort {
  Flux<Book> findAll();

  Mono<Book> findById(String id);

  Flux<Book> findByTitleContainsIgnoreCase(String titlePart);

  Flux<Book> findByAuthorContainsIgnoreCase(String authorPart);

  Flux<Book> saveAll(Iterable<Book> books);

  Mono<Book> save(Book book);
}
