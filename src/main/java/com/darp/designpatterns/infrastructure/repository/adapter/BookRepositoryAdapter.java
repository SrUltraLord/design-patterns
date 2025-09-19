package com.darp.designpatterns.infrastructure.repository.adapter;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.ports.BookRepositoryPort;
import com.darp.designpatterns.infrastructure.repository.jpa.BookJpaRepository;
import com.darp.designpatterns.infrastructure.repository.mapper.BookEntityMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BookRepositoryAdapter implements BookRepositoryPort {
  private final BookJpaRepository jpaRepository;
  private final BookEntityMapper mapper;

  @Override
  public Flux<Book> findAll() {
    return Flux.defer(() -> Flux.fromIterable(jpaRepository.findAll()).map(mapper::toDomain));
  }

  @Override
  public Flux<Book> findByTitleContainsIgnoreCase(String titlePart) {
    return Flux.defer(
        () ->
            Flux.fromIterable(jpaRepository.findByTitleContainingIgnoreCase(titlePart))
                .map(mapper::toDomain));
  }

  @Override
  public Flux<Book> findByAuthorContainsIgnoreCase(String authorPart) {
    return Flux.defer(
        () ->
            Flux.fromIterable(jpaRepository.findByAuthorContainingIgnoreCase(authorPart))
                .map(mapper::toDomain));
  }

  @Override
  public Flux<Book> saveAll(Iterable<Book> books) {
    return Flux.defer(
        () -> {
          List<Book> list = (List<Book>) books;
          var entities = list.stream().map(mapper::toEntity).toList();
          var saved = jpaRepository.saveAll(entities);
          return Flux.fromIterable(saved).map(mapper::toDomain);
        });
  }

  @Override
  public Mono<Book> findById(String id) {
    return Mono.fromCallable(() -> jpaRepository.findById(id))
        .flatMap(result -> result.map(mapper::toDomain).map(Mono::just).orElse(Mono.empty()));
  }

  @Override
  public Mono<Book> save(Book book) {
    return Mono.defer(
        () -> {
          var entity = mapper.toEntity(book);
          var saved = jpaRepository.save(entity);
          return Mono.just(mapper.toDomain(saved));
        });
  }
}
