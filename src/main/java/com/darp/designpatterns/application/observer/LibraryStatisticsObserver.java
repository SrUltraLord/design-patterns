package com.darp.designpatterns.application.observer;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.patterns.observer.Observer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryStatisticsObserver implements Observer<Book> {

  private int totalLoans = 0;
  private int totalReturns = 0;

  @Override
  public void update(Book book) {
    switch (book.getStatus()) {
      case BORROWED -> {
        totalLoans++;
        log.info("📊 STATISTICS: Total books loaned today: {}", totalLoans);
      }
      case AVAILABLE -> {
        totalReturns++;
        log.info("📊 STATISTICS: Total books returned today: {}", totalReturns);
      }
    }
  }

  public int getTotalLoans() {
    return totalLoans;
  }

  public int getTotalReturns() {
    return totalReturns;
  }
}
