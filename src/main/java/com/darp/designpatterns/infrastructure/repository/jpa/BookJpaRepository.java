package com.darp.designpatterns.infrastructure.repository.jpa;

import com.darp.designpatterns.infrastructure.repository.entity.BookEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity, String> {
  List<BookEntity> findByTitleContainingIgnoreCase(String titlePart);

  List<BookEntity> findByAuthorContainingIgnoreCase(String authorPart);
}
