package com.darp.designpatterns.infrastructure.repository.entity;

import com.darp.designpatterns.domain.models.BookFormat;
import com.darp.designpatterns.domain.models.BookGenre;
import com.darp.designpatterns.domain.models.BookStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity {
  @Id private String id;
  private String title;
  private String author;

  @Enumerated(EnumType.STRING)
  private BookGenre genre;

  @Enumerated(EnumType.STRING)
  private BookFormat format;

  @Enumerated(EnumType.STRING)
  private BookStatus status;
}
