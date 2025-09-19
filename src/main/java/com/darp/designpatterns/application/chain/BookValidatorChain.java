package com.darp.designpatterns.application.chain;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.patterns.chain.AuthorValidator;
import com.darp.designpatterns.domain.patterns.chain.TitleValidator;
import com.darp.designpatterns.domain.patterns.chain.Validator;
import org.springframework.stereotype.Component;

@Component
public class BookValidatorChain {
  private final Validator chain;

  public BookValidatorChain() {
    TitleValidator titleValidator = new TitleValidator();
    AuthorValidator authorValidator = new AuthorValidator();
    titleValidator.setNext(authorValidator);
    this.chain = titleValidator;
  }

  public void validate(Book book) {
    chain.validate(book);
  }
}
