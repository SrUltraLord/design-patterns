package com.darp.designpatterns.application.factory;

import com.darp.designpatterns.domain.models.*;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.UUID;

@Valid
public class NonFictionBookFactory implements BookFactory {
  private static final BookStatus DEFAULT_STATUS = BookStatus.AVAILABLE;
  private static final BookFormat DEFAULT_FORMAT = BookFormat.HARDCOVER;

  @Override
  public Book createBook(
      @NotBlank String title,
      @NotBlank String author,
      @Nullable BookFormat format,
      @Nullable BookStatus status) {

    BookFormat finalFormat = Optional.ofNullable(format).orElse(DEFAULT_FORMAT);
    BookStatus finalStatus = Optional.ofNullable(status).orElse(DEFAULT_STATUS);

    return new Book.Builder()
        .id(UUID.randomUUID().toString())
        .title(title)
        .author(author)
        .genre(BookGenre.NON_FICTION)
        .format(finalFormat)
        .status(finalStatus)
        .build();
  }
}
