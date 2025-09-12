package com.darp.designpatterns.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Book {
  private String id;
  private String title;
  private String author;
  private BookGenre genre;
  private BookFormat format;
  private BookStatus status;

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
