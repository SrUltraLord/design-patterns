package com.darp.designpatterns.application.ports;

import reactor.core.publisher.Mono;

public interface LoanBookUseCase {
  Mono<Boolean> loanBook(String bookId, String borrower);

  Mono<Boolean> returnBook(String bookId);
}
