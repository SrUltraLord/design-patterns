package com.darp.designpatterns.application.factory;

import com.darp.designpatterns.domain.models.BookType;
import org.springframework.stereotype.Component;

@Component
public class BookFactoryProvider {
  private final FictionBookFactory fictionFactory = new FictionBookFactory();
  private final NonFictionBookFactory nonFictionFactory = new NonFictionBookFactory();

  public BookFactory getFactory(BookType type) {
    return switch (type) {
      case FICTION -> fictionFactory;
      case NON_FICTION -> nonFictionFactory;
    };
  }
}
