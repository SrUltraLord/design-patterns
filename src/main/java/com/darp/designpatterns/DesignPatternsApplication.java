package com.darp.designpatterns;

import com.darp.designpatterns.application.factory.BookFactoryProvider;
import com.darp.designpatterns.application.ports.SearchBookUseCase;
import com.darp.designpatterns.domain.models.*;
import com.darp.designpatterns.domain.ports.BookRepositoryPort;
import reactor.core.publisher.Hooks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DesignPatternsApplication {
  public static void main(String[] args) {
    SpringApplication.run(DesignPatternsApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(
      BookFactoryProvider provider,
      SearchBookUseCase searchUseCase,
      BookRepositoryPort repositoryPort) {
    return args -> {
      Hooks.enableAutomaticContextPropagation();
      var fictionFactory = provider.getFactory(BookType.FICTION);
      var nonFictionFactory = provider.getFactory(BookType.NON_FICTION);

      var fictionBook =
          fictionFactory.createBook(
              "The Hobbit", "J.R.R. Tolkien", BookFormat.HARDCOVER, BookStatus.AVAILABLE);

      var nonFictionBook =
          nonFictionFactory.createBook(
              "Clean Architecture", "Robert C. Martin", BookFormat.PAPERBACK, null);

      // Seed DB (idempotent simple approach: just save both every start)
      repositoryPort
          .saveAll(java.util.List.of(fictionBook, nonFictionBook))
          .thenMany(searchUseCase.searchByField("title", "hobbit"))
          .collectList()
          .doOnNext(list -> log.info("Search by title 'hobbit': {}", list))
          .thenMany(searchUseCase.searchByField("author", "martin"))
          .collectList()
          .doOnNext(list -> log.info("Search by author 'martin': {}", list))
          .thenMany(repositoryPort.findAll())
          .collectList()
          .doOnNext(list -> log.info("All books: {}", list))
          .block();
    };
  }
}
