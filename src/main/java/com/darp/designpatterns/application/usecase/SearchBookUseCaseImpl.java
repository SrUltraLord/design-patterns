package com.darp.designpatterns.application.usecase;

import com.darp.designpatterns.application.ports.SearchBookUseCase;
import com.darp.designpatterns.application.search.ReactiveBookSearchStrategyProvider;
import com.darp.designpatterns.domain.models.Book;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SearchBookUseCaseImpl implements SearchBookUseCase {
  private final ReactiveBookSearchStrategyProvider strategyProvider;

  @Override
  public Flux<Book> searchByField(String field, String value) {
    String normalized = field == null ? null : field.trim().toLowerCase();

    return Optional.ofNullable(strategyProvider.get(normalized))
        .map(strategy -> strategy.search(value))
        .orElseGet(Flux::empty);
  }
}
