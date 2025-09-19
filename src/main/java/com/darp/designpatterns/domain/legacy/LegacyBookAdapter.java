package com.darp.designpatterns.domain.legacy;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.models.BookFormat;
import com.darp.designpatterns.domain.models.BookGenre;
import com.darp.designpatterns.domain.models.BookStatus;
import lombok.Getter;

@Getter
public class LegacyBookAdapter extends Book {
  private final LegacyBook legacyBook;

  public LegacyBookAdapter(LegacyBook legacyBook) {
    super(
        legacyBook.code(),
        legacyBook.name(),
        legacyBook.author(),
        parseGenre(legacyBook.type()),
        parseFormat(legacyBook.format()),
        parseStatus(legacyBook.status()));
    this.legacyBook = legacyBook;
  }

  private static BookGenre parseGenre(String type) {
    return "Ficcion".equalsIgnoreCase(type) ? BookGenre.FICTION : BookGenre.NON_FICTION;
  }

  private static BookFormat parseFormat(String format) {
    return "Fisico".equalsIgnoreCase(format) ? BookFormat.HARDCOVER : BookFormat.PAPERBACK;
  }

  private static BookStatus parseStatus(String status) {
    return "Prestado".equalsIgnoreCase(status) ? BookStatus.BORROWED : BookStatus.AVAILABLE;
  }
}
