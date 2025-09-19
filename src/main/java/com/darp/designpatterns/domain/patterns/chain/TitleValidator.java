package com.darp.designpatterns.domain.patterns.chain;

import com.darp.designpatterns.domain.models.Book;

public class TitleValidator implements Validator {
  private Validator next;

  @Override
  public void setNext(Validator next) {
    this.next = next;
  }

  @Override
  public void validate(Book book) {
    if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
      throw new IllegalArgumentException("Book title must not be empty");
    }
    if (next != null) {
      next.validate(book);
    }
  }
}
