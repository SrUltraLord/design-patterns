package com.darp.designpatterns.domain.models;

import com.darp.designpatterns.domain.patterns.observer.Observer;
import com.darp.designpatterns.domain.patterns.observer.Subject;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Book implements Subject<Book> {
  private String id;
  private String title;
  private String author;
  private BookGenre genre;
  private BookFormat format;
  private BookStatus status;
  private final List<Observer<Book>> observers = new ArrayList<>();

  public Book(
      String id,
      String title,
      String author,
      BookGenre genre,
      BookFormat format,
      BookStatus status) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.genre = genre;
    this.format = format;
    this.status = status;
  }

  public void setStatus(BookStatus newStatus) {
    if (this.status != newStatus) {
      this.status = newStatus;
      notifyObservers();
    }
  }

  @Override
  public void addObserver(Observer<Book> observer) {
    if (!observers.contains(observer)) {
      observers.add(observer);
    }
  }

  @Override
  public void removeObserver(Observer<Book> observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer<Book> observer : observers) {
      observer.update(this);
    }
  }

  // Package-private getter for testing
  List<Observer<Book>> getObservers() {
    return observers;
  }

  public static class Builder {
    private String id;
    private String title;
    private String author;
    private BookGenre genre;
    private BookFormat format;
    private BookStatus status;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder author(String author) {
      this.author = author;
      return this;
    }

    public Builder genre(BookGenre genre) {
      this.genre = genre;
      return this;
    }

    public Builder format(BookFormat format) {
      this.format = format;
      return this;
    }

    public Builder status(BookStatus status) {
      this.status = status;
      return this;
    }

    public Book build() {
      return new Book(id, title, author, genre, format, status);
    }
  }
}
