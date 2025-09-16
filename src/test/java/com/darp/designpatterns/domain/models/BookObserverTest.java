package com.darp.designpatterns.domain.models;

import com.darp.designpatterns.domain.patterns.observer.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
class BookObserverTest {

  @Mock private Observer<Book> mockObserver1;

  @Mock private Observer<Book> mockObserver2;

  private Book book;

  @BeforeEach
  void setUp() {
    book =
        new Book(
            "1",
            "Test Book",
            "Test Author",
            BookGenre.FICTION,
            BookFormat.PAPERBACK,
            BookStatus.AVAILABLE);
  }

  @Test
  void shouldAddObserver() {
    // When
    book.addObserver(mockObserver1);

    // Then
    assertTrue(book.getObservers().contains(mockObserver1));
  }

  @Test
  void shouldNotAddDuplicateObserver() {
    // When
    book.addObserver(mockObserver1);
    book.addObserver(mockObserver1); // Add same observer twice

    // Then
    assertEquals(1, book.getObservers().size());
    assertTrue(book.getObservers().contains(mockObserver1));
  }

  @Test
  void shouldRemoveObserver() {
    // Given
    book.addObserver(mockObserver1);
    book.addObserver(mockObserver2);

    // When
    book.removeObserver(mockObserver1);

    // Then
    assertFalse(book.getObservers().contains(mockObserver1));
    assertTrue(book.getObservers().contains(mockObserver2));
  }

  @Test
  void shouldNotifyObserversOnStatusChange() {
    // Given
    book.addObserver(mockObserver1);
    book.addObserver(mockObserver2);

    // When
    book.setStatus(BookStatus.BORROWED);

    // Then
    verify(mockObserver1).update(book);
    verify(mockObserver2).update(book);
    assertEquals(BookStatus.BORROWED, book.getStatus());
  }

  @Test
  void shouldNotNotifyObserversWhenStatusUnchanged() {
    // Given
    book.addObserver(mockObserver1);
    book.setStatus(BookStatus.AVAILABLE); // Same as initial status

    // When
    book.setStatus(BookStatus.AVAILABLE); // No change

    // Then
    verify(mockObserver1, never()).update(book);
  }

  @Test
  void shouldNotifyObserversOnlyWhenStatusActuallyChanges() {
    // Given
    book.addObserver(mockObserver1);

    // When
    book.setStatus(BookStatus.BORROWED); // Change from AVAILABLE to BORROWED
    book.setStatus(BookStatus.BORROWED); // No change
    book.setStatus(BookStatus.AVAILABLE); // Change from BORROWED to AVAILABLE

    // Then
    verify(mockObserver1, times(2)).update(book); // Only 2 actual changes
  }
}
