package com.darp.designpatterns.domain.patterns.chain;

import com.darp.designpatterns.domain.models.Book;

public class AuthorValidator implements Validator {
  private Validator next;

  @Override
  public void setNext(Validator next) {
    this.next = next;
  }

  @Override
  public void validate(Book book) {
    if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
      throw new IllegalArgumentException("Book author must not be empty");
    }
    if (next != null) {
      next.validate(book);
    }
  }
}
