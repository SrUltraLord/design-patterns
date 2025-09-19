package com.darp.designpatterns.infrastructure.web;

import com.darp.designpatterns.infrastructure.web.mapper.BookDtoMapper;
import com.darp.designpatterns.application.dto.BookRequestDTO;
import com.darp.designpatterns.application.dto.BookResponseDTO;
import com.darp.designpatterns.application.ports.LoanBookUseCase;
import com.darp.designpatterns.application.ports.SearchBookUseCase;
import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.ports.BookRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
  private final BookRepositoryPort repositoryPort;
  private final SearchBookUseCase searchBookUseCase;
  private final LoanBookUseCase loanBookUseCase;
  private final BookDtoMapper bookDtoMapper;

  @GetMapping
  public Flux<BookResponseDTO> getAllBooks() {
    return repositoryPort.findAll().map(bookDtoMapper::toResponseDTO);
  }

  @GetMapping("/search")
  public Flux<BookResponseDTO> searchBooks(@RequestParam String field, @RequestParam String value) {
    return searchBookUseCase.searchByField(field, value).map(bookDtoMapper::toResponseDTO);
  }

  @PostMapping
  public Mono<BookResponseDTO> addBook(@RequestBody BookRequestDTO request) {
    Book book = bookDtoMapper.toDomain(request);
    return repositoryPort.save(book).map(bookDtoMapper::toResponseDTO);
  }

  @PostMapping("/{id}/loan")
  public Mono<ResponseEntity<String>> loanBook(
      @PathVariable String id, @RequestParam String borrower) {
    return loanBookUseCase
        .loanBook(id, borrower)
        .map(
            success ->
                success
                    ? ResponseEntity.ok("Book loaned successfully.")
                    : ResponseEntity.badRequest().body("Book could not be loaned."));
  }

  @PostMapping("/{id}/return")
  public Mono<ResponseEntity<String>> returnBook(@PathVariable String id) {
    return loanBookUseCase
        .returnBook(id)
        .map(
            success ->
                success
                    ? ResponseEntity.ok("Book returned successfully.")
                    : ResponseEntity.badRequest().body("Book could not be returned."));
  }
}
