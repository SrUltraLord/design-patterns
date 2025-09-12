package com.darp.designpatterns.infrastructure.repository.mapper;

import com.darp.designpatterns.domain.models.*;
import com.darp.designpatterns.infrastructure.repository.entity.BookEntity;



package com.darp.designpatterns.infrastructure.repository.mapper;

import com.darp.designpatterns.domain.models.Book;
import com.darp.designpatterns.infrastructure.repository.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookEntityMapper {
  BookEntity toEntity(Book book);
  Book toDomain(BookEntity entity);
}
