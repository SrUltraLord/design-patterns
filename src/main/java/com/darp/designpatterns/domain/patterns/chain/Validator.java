package com.darp.designpatterns.domain.patterns.chain;

import com.darp.designpatterns.domain.models.Book;

public interface Validator {
  void setNext(Validator next);

  void validate(Book book);
}
