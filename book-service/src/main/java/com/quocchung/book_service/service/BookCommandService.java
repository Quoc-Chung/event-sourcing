package com.quocchung.book_service.service;

import com.quocchung.book_service.command.UpdateBookCommand;
import com.quocchung.book_service.dtos.requests.BookRequest;

public interface BookCommandService {
  String createBook(BookRequest request);

  void  updateBook(String id, BookRequest request);

  void  deleteBook(String id);


}
