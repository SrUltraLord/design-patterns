package com.darp.designpatterns;

import com.darp.designpatterns.application.factory.BookFactoryProvider;
import com.darp.designpatterns.application.ports.SearchBookUseCase;
import com.darp.designpatterns.application.service.BookLoanService;
import com.darp.designpatterns.application.service.BookObserverService;
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
      BookRepositoryPort repositoryPort,
      BookLoanService loanService,
      BookObserverService observerService) {
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

      log.info("🚀 Starting Design Patterns Demo - Observer Pattern Implementation");

      // Seed DB and register observers
      repositoryPort
          .saveAll(java.util.List.of(fictionBook, nonFictionBook))
          .doOnNext(book -> log.info("📖 Book saved: {}", book.getTitle()))
          .then(observerService.registerObserversForAllBooks())

          // Demonstrate search functionality
          .thenMany(searchUseCase.searchByField("title", "hobbit"))
          .collectList()
          .doOnNext(list -> log.info("🔍 Search by title 'hobbit': {}", list.size() + " results"))
          .thenMany(searchUseCase.searchByField("author", "martin"))
          .collectList()
          .doOnNext(list -> log.info("🔍 Search by author 'martin': {}", list.size() + " results"))

          // Demonstrate Observer pattern with loan operations
          .then(loanService.borrowBook(fictionBook.getId(), "Alice Johnson"))
          .doOnNext(book -> log.info("✅ Successfully borrowed: {}", book.getTitle()))
          .then(loanService.borrowBook(nonFictionBook.getId(), "Bob Smith"))
          .doOnNext(book -> log.info("✅ Successfully borrowed: {}", book.getTitle()))
          .then(loanService.returnBook(fictionBook.getId()))
          .doOnNext(book -> log.info("✅ Successfully returned: {}", book.getTitle()))
          .then(loanService.reserveBook(fictionBook.getId(), "Charlie Brown"))
          .doOnNext(book -> log.info("✅ Successfully reserved: {}", book.getTitle()))

          // Show final status
          .thenMany(repositoryPort.findAll())
          .collectList()
          .doOnNext(
              list -> {
                log.info("📚 Final library status:");
                list.forEach(
                    book ->
                        log.info(
                            "  - '{}' by {} [{}]",
                            book.getTitle(),
                            book.getAuthor(),
                            book.getStatus()));
              })
          .block();
    };
  }
}
