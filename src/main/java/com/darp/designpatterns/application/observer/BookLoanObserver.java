package com.darp.designpatterns.application.observer;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.models.BookStatus;
import com.darp.designpatterns.domain.patterns.observer.Observer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookLoanObserver implements Observer<Book> {

  @Override
  public void update(Book book) {
    if (book.getStatus() == BookStatus.BORROWED) {
      log.info(
          "📚 LOAN NOTIFICATION: Book '{}' by {} has been borrowed",
          book.getTitle(),
          book.getAuthor());
      // Here you could add additional logic like:
      // - Sending email notifications
      // - Updating loan statistics
      // - Recording loan history
    } else if (book.getStatus() == BookStatus.AVAILABLE) {
      log.info(
          "📖 RETURN NOTIFICATION: Book '{}' by {} has been returned and is now available",
          book.getTitle(),
          book.getAuthor());
    }
  }
}
