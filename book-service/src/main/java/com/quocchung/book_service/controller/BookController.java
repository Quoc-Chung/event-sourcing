package com.quocchung.book_service.controller;

import com.quocchung.book_service.dtos.requests.BookRequest;
import com.quocchung.book_service.dtos.response.BookResponse;
import com.quocchung.book_service.service.BookCommandService;
import com.quocchung.book_service.service.BookQueryService;
import com.quocchung.common_service.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
   public ResponseEntity<ApiResponse<String>> addBook(@RequestBody BookRequest bookRequest){
     String bookId = bookCommandService.createBook(bookRequest);
     return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Tạo thành công sách có id = "+ bookId));
   }


  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable String id,
      @RequestBody BookRequest request) {
    bookCommandService.updateBook(id, request);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Update thành công sách có id = " + id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable String id) {
    bookCommandService.deleteBook(id);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Xóa thành công sách có id = "+ id));
  }


  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<BookResponse>> getOne(@PathVariable String id) {
    return ResponseEntity.ok(ApiResponse.success(bookQueryService.getBookById(id)));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<BookResponse>>> getAll() {
    return ResponseEntity.ok(ApiResponse.success(bookQueryService.getAllBooks()));
  }

}
