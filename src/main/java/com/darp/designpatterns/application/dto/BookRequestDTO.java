package com.darp.designpatterns.application.dto;

import com.darp.designpatterns.domain.models.BookFormat;
import com.darp.designpatterns.domain.models.BookGenre;
import com.darp.designpatterns.domain.models.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Genre is required")
    private BookGenre genre;

    @NotNull(message = "Format is required")
    private BookFormat format;

    @NotNull(message = "Status is required")
    private BookStatus status;
}
