package com.darp.designpatterns.application;

import static org.junit.jupiter.api.Assertions.*;

import com.darp.designpatterns.application.factory.FictionBookFactory;
import com.darp.designpatterns.application.factory.NonFictionBookFactory;
import com.darp.designpatterns.domain.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookFactoryTests {

  @Test
  @DisplayName("Crea libro de ficción con género FICTION")
  void fictionFactoryCreatesFictionBook() {
    var factory = new FictionBookFactory();
    var book = factory.createBook("Title", "Author", BookFormat.EBOOK, BookStatus.AVAILABLE);
    assertNotNull(book.getId());
    assertEquals(BookGenre.FICTION, book.getGenre());
    assertEquals("Title", book.getTitle());
  }

  @Test
  @DisplayName("Crea libro no ficción con género NON_FICTION")
  void nonFictionFactoryCreatesNonFiction() {
    var factory = new NonFictionBookFactory();
    var book = factory.createBook("Clean Architecture", "Robert C. Martin", BookFormat.EBOOK, null);
    assertEquals(BookGenre.NON_FICTION, book.getGenre());
    assertEquals(BookStatus.AVAILABLE, book.getStatus());
  }
}
