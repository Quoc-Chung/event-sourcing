package com.quocchung.book_service.utils;

import com.quocchung.book_service.dtos.response.BookResponse;
import com.quocchung.book_service.model.Book;
import org.springframework.stereotype.Component;

@Component
public class Converter {
  public BookResponse toBookDTO(Book book){
    return BookResponse.builder()
        .id(book.getId())
        .name(book.getName())
        .author(book.getAuthor())
        .isReady(book.getIsReady())
        .build();
  }
}
