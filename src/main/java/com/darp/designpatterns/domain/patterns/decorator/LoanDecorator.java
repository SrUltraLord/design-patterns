package com.darp.designpatterns.domain.patterns.decorator;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.models.BookStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoanDecorator extends BookDecorator {
  public LoanDecorator(Book book) {
    super(book);
  }

  public boolean loan(String borrower) {
    if (book.getStatus() == BookStatus.AVAILABLE) {
      log.info("Loaning book '{}' to '{}'", book.getTitle(), borrower);
      book.setStatus(BookStatus.BORROWED);
      // You could add logic to track borrower, loan date, etc.

      return true;
    }

    log.warn("Book '{}' is not available for loan", book.getTitle());

    return false;
  }

  public boolean returnBook() {
    if (book.getStatus() == BookStatus.BORROWED) {
      book.setStatus(BookStatus.AVAILABLE);
      return true;
    }
    return false;
  }

  // Optionally override methods to add more behavior
}
