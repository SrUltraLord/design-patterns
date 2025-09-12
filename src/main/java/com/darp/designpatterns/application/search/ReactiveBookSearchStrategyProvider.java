package com.darp.designpatterns.application.search;

import com.darp.designpatterns.domain.ports.BookRepositoryPort;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ReactiveBookSearchStrategyProvider {
  private final Map<String, ReactiveBookSearchStrategy> strategies = new ConcurrentHashMap<>();

  public ReactiveBookSearchStrategyProvider(BookRepositoryPort repositoryPort) {
    strategies.put("title", new TitleReactiveStrategy(repositoryPort));
    strategies.put("author", new AuthorReactiveStrategy(repositoryPort));
  }

  public ReactiveBookSearchStrategy get(String field) {
    if (field == null) return null;
    return strategies.get(field.toLowerCase());
  }
}
