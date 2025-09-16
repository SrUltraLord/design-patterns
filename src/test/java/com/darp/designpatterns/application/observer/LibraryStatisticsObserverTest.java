package com.darp.designpatterns.application.observer;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.domain.models.BookFormat;
import com.darp.designpatterns.domain.models.BookGenre;
import com.darp.designpatterns.domain.models.BookStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LibraryStatisticsObserverTest {

  @InjectMocks private LibraryStatisticsObserver statisticsObserver;

  private Book testBook;

  @BeforeEach
  void setUp() {
    testBook =
        new Book(
            "1",
            "Test Book",
            "Test Author",
            BookGenre.FICTION,
            BookFormat.PAPERBACK,
            BookStatus.AVAILABLE);
  }

  @Test
  void shouldIncrementLoansWhenBookBorrowed() {
    // When
    testBook.setStatus(BookStatus.BORROWED);
    statisticsObserver.update(testBook);

    // Then
    assertEquals(1, statisticsObserver.getTotalLoans());
    assertEquals(0, statisticsObserver.getTotalReturns());
  }

  @Test
  void shouldIncrementReturnsWhenBookReturned() {
    // When
    testBook.setStatus(BookStatus.AVAILABLE);
    statisticsObserver.update(testBook);

    // Then
    assertEquals(0, statisticsObserver.getTotalLoans());
    assertEquals(1, statisticsObserver.getTotalReturns());
  }

  @Test
  void shouldTrackMultipleLoansAndReturns() {
    // When
    testBook.setStatus(BookStatus.BORROWED);
    statisticsObserver.update(testBook);

    testBook.setStatus(BookStatus.AVAILABLE);
    statisticsObserver.update(testBook);

    testBook.setStatus(BookStatus.BORROWED);
    statisticsObserver.update(testBook);

    // Then
    assertEquals(2, statisticsObserver.getTotalLoans());
    assertEquals(1, statisticsObserver.getTotalReturns());
  }
}
