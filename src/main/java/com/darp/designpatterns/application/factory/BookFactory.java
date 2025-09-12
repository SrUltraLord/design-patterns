package com.darp.designpatterns.application.factory;

import com.darp.designpatterns.domain.models.*;

public interface BookFactory {
  Book createBook(String title, String author, BookFormat format, BookStatus status);
}
