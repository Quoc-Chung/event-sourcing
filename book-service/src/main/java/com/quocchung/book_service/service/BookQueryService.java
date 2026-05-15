package com.quocchung.book_service.service;

import com.quocchung.book_service.dtos.response.BookResponse;
import java.util.List;

public interface BookQueryService {

  BookResponse getBookById(String id);

  List<BookResponse> getAllBooks();
}
