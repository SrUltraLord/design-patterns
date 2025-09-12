package com.darp.designpatterns.application.ports;

import com.darp.designpatterns.domain.models.Book;
import reactor.core.publisher.Flux;

public interface SearchBookUseCase {
  Flux<Book> searchByField(String field, String value);
}
