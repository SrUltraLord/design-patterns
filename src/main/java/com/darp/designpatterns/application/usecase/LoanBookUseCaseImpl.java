package com.darp.designpatterns.application.usecase;

import com.darp.designpatterns.application.ports.LoanBookUseCase;
import com.darp.designpatterns.domain.models.BookStatus;
import com.darp.designpatterns.domain.patterns.decorator.LoanDecorator;
import com.darp.designpatterns.domain.ports.BookRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LoanBookUseCaseImpl implements LoanBookUseCase {
  private final BookRepositoryPort repositoryPort;

  @Override
  public Mono<Boolean> loanBook(String bookId, String borrower) {
    return repositoryPort
        .findById(bookId)
        .map(LoanDecorator::new)
        .flatMap(
            decorator -> {
              var isLoaned = decorator.loan(borrower);
              if (!isLoaned) {
                return Mono.just(false);
              }

              return repositoryPort
                  .findById(bookId)
                  .flatMap(
                      book -> {
                        book.setStatus(BookStatus.BORROWED);
                        return repositoryPort.save(book).thenReturn(true);
                      });
            });
  }

  @Override
  public Mono<Boolean> returnBook(String bookId) {
    return repositoryPort
        .findById(bookId)
        .map(LoanDecorator::new)
        .flatMap(
            decorator -> {
              var isReturned = decorator.returnBook();
              if (!isReturned) {
                return Mono.just(false);
              }

              return repositoryPort
                  .findById(bookId)
                  .flatMap(
                      book -> {
                        book.setStatus(BookStatus.AVAILABLE);
                        return repositoryPort.save(book).thenReturn(true);
                      });
            });
  }
}
