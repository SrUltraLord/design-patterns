package com.darp.designpatterns.application.factory;

import com.darp.designpatterns.domain.models.BookType;
import com.darp.designpatterns.application.chain.BookValidatorChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookFactoryProvider {
  private final FictionBookFactory fictionFactory = new FictionBookFactory();
  private final NonFictionBookFactory nonFictionFactory = new NonFictionBookFactory();
  private final BookValidatorChain validatorChain;

  @Autowired
  public BookFactoryProvider(BookValidatorChain validatorChain) {
    this.validatorChain = validatorChain;
  }

  public BookFactory getFactory(BookType type) {
    return (title, author, format, status) -> {
      // Validate before creating
      var book =
          switch (type) {
            case FICTION -> fictionFactory.createBook(title, author, format, status);
            case NON_FICTION -> nonFictionFactory.createBook(title, author, format, status);
          };
      validatorChain.validate(book);
      return book;
    };
  }
}
