package com.darp.designpatterns.domain.patterns.decorator;

import com.darp.designpatterns.domain.models.Book;

public abstract class BookDecorator extends Book {
  protected final Book book;

  public BookDecorator(Book book) {
    super(
        book.getId(),
        book.getTitle(),
        book.getAuthor(),
        book.getGenre(),
        book.getFormat(),
        book.getStatus());
    this.book = book;
  }

  public Book getWrappedBook() {
    return book;
  }
}
