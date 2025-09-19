package com.darp.designpatterns.application.dto;

import com.darp.designpatterns.domain.models.BookFormat;
import com.darp.designpatterns.domain.models.BookGenre;
import com.darp.designpatterns.domain.models.BookStatus;
import lombok.Data;

@Data
public class BookRequestDTO {
    private String title;
    private String author;
    private BookGenre genre;
    private BookFormat format;
    private BookStatus status;
}
