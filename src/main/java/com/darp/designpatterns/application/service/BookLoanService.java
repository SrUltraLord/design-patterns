package com.darp.designpatterns.application.service;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.models.BookStatus;
import com.darp.designpatterns.domain.ports.BookRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookLoanService {

  private final BookRepositoryPort repositoryPort;

  public Mono<Book> borrowBook(String bookId, String borrowerName) {
    return repositoryPort
        .findById(bookId)
        .switchIfEmpty(
            Mono.error(new IllegalArgumentException("Book with ID " + bookId + " not found")))
        .flatMap(
            book -> {
              if (book.getStatus() != BookStatus.AVAILABLE) {
                return Mono.error(
                    new IllegalStateException(
                        "Book '" + book.getTitle() + "' is not available for borrowing"));
              }

              log.info(
                  "Processing loan for book '{}' to borrower: {}", book.getTitle(), borrowerName);
              book.setStatus(BookStatus.BORROWED);

              return repositoryPort
                  .save(book)
                  .doOnSuccess(
                      savedBook ->
                          log.info(
                              "Book '{}' successfully borrowed by {}",
                              savedBook.getTitle(),
                              borrowerName));
            });
  }

  public Mono<Book> returnBook(String bookId) {
    return repositoryPort
        .findById(bookId)
        .switchIfEmpty(
            Mono.error(new IllegalArgumentException("Book with ID " + bookId + " not found")))
        .flatMap(
            book -> {
              if (book.getStatus() != BookStatus.BORROWED) {
                return Mono.error(
                    new IllegalStateException(
                        "Book '" + book.getTitle() + "' is not currently borrowed"));
              }

              log.info("Processing return for book '{}'", book.getTitle());
              book.setStatus(BookStatus.AVAILABLE);

              return repositoryPort
                  .save(book)
                  .doOnSuccess(
                      savedBook ->
                          log.info("Book '{}' successfully returned", savedBook.getTitle()));
            });
  }

  public Mono<Book> reserveBook(String bookId, String reserverName) {
    return repositoryPort
        .findById(bookId)
        .switchIfEmpty(
            Mono.error(new IllegalArgumentException("Book with ID " + bookId + " not found")))
        .flatMap(
            book -> {
              if (book.getStatus() != BookStatus.AVAILABLE) {
                return Mono.error(
                    new IllegalStateException(
                        "Book '" + book.getTitle() + "' is not available for reservation"));
              }

              log.info(
                  "Processing reservation for book '{}' by: {}", book.getTitle(), reserverName);
              book.setStatus(BookStatus.RESERVED);

              return repositoryPort
                  .save(book)
                  .doOnSuccess(
                      savedBook ->
                          log.info(
                              "Book '{}' successfully reserved by {}",
                              savedBook.getTitle(),
                              reserverName));
            });
  }
}
