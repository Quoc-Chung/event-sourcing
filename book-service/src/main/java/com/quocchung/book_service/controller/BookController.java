package com.quocchung.book_service.controller;

import com.quocchung.book_service.dtos.requests.BookRequest;
import com.quocchung.book_service.dtos.response.BookResponse;
import com.quocchung.book_service.service.BookCommandService;
import com.quocchung.book_service.service.BookQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

   private final BookCommandService bookCommandService;
   private final BookQueryService bookQueryService;


   @PostMapping
   public ResponseEntity<String> addBook(@RequestBody BookRequest bookRequest){
     String bookId = bookCommandService.createBook(bookRequest);
     return ResponseEntity.status(HttpStatus.CREATED).body("Tạo thành công sách có id = "+ bookId);
   }


  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable String id,
      @RequestBody BookRequest request) {
    bookCommandService.updateBook(id, request);
    return ResponseEntity.status(HttpStatus.OK).body("Update thành công sách có id = " + id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable String id) {
    bookCommandService.deleteBook(id);
    return ResponseEntity.status(HttpStatus.OK).body("Xóa thành công sách có id = "+ id);
  }


  @GetMapping("/{id}")
  public ResponseEntity<BookResponse> getOne(@PathVariable String id) {
    return ResponseEntity.ok(bookQueryService.getBookById(id));
  }

  @GetMapping
  public ResponseEntity<List<BookResponse>> getAll() {
    return ResponseEntity.ok(bookQueryService.getAllBooks());
  }


}
