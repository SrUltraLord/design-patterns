package com.darp.designpatterns.infrastructure.web.mapper;

import com.darp.designpatterns.application.dto.BookRequestDTO;
import com.darp.designpatterns.application.dto.BookResponseDTO;
import com.darp.designpatterns.domain.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookDtoMapper {
  @Mapping(target = "id", ignore = true)
  Book toDomain(BookRequestDTO dto);

  BookResponseDTO toResponseDTO(Book book);
}
