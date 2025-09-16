package com.darp.designpatterns.application.service;

import com.darp.designpatterns.application.observer.BookLoanObserver;
import com.darp.designpatterns.application.observer.LibraryStatisticsObserver;
import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.ports.BookRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookObserverService {

  private final BookRepositoryPort repositoryPort;
  private final BookLoanObserver loanObserver;
  private final LibraryStatisticsObserver statisticsObserver;

  public Mono<Void> registerObserversForBook(String bookId) {
    return repositoryPort
        .findById(bookId)
        .doOnNext(
            book -> {
              book.addObserver(loanObserver);
              book.addObserver(statisticsObserver);
              log.info("Observers registered for book: {}", book.getTitle());
            })
        .then();
  }

  public Mono<Void> registerObserversForAllBooks() {
    return repositoryPort
        .findAll()
        .doOnNext(
            book -> {
              book.addObserver(loanObserver);
              book.addObserver(statisticsObserver);
            })
        .doOnComplete(() -> log.info("Observers registered for all books in the library"))
        .then();
  }
}
