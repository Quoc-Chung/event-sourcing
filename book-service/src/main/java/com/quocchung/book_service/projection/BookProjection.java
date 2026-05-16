package com.quocchung.book_service.projection;

import com.quocchung.book_service.dtos.response.BookResponse;
import com.quocchung.book_service.event.BookCreatedEvent;
import com.quocchung.book_service.event.BookDeletedEvent;
import com.quocchung.book_service.event.BookUpdatedEvent;
import com.quocchung.book_service.model.Book;
import com.quocchung.book_service.query.FindAllBooksQuery;
import com.quocchung.book_service.query.FindBookByIdQuery;
import com.quocchung.book_service.repository.BookRepository;
import com.quocchung.book_service.utils.Converter;
import com.quocchung.common_service.exception.NotFoundException;
import com.quocchung.common_service.utils.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("book-projection")
@RequiredArgsConstructor
public class BookProjection {

  private final BookRepository bookRepository;
  private final Converter converter;


  /**
   * EVENT HANDLE
   * @param event
   */
  @EventHandler
  public void on(BookCreatedEvent event) {
    Book book = Book.builder()
        .id(event.getBookId())
        .name(event.getName())
        .author(event.getAuthor())
        .isReady(event.getIsReady())
        .build();
    bookRepository.saveAndFlush(book);
  }

  @EventHandler
  public void on(BookUpdatedEvent event) {
    bookRepository.findById(event.getBookId())

        .ifPresent(book -> {
          book.setName(event.getName());
          book.setAuthor(event.getAuthor());
          book.setIsReady(event.getIsReady());
          bookRepository.saveAndFlush(book);
        })
        ;
  }

  @EventHandler
  public void on(BookDeletedEvent event) {
    bookRepository.deleteById(event.getBookId());
  }

  @QueryHandler
  public BookResponse handle(FindBookByIdQuery query) {
    Book book = bookRepository.findById(query.getBookId())
        .orElseThrow(() -> new NotFoundException(ErrorCode.BOOK_NOT_FOUND,"Không tìm thấy sách với id : " + query.getBookId()));
    return converter.toBookDTO(book);
  }


  @QueryHandler
  public List<BookResponse> handle(FindAllBooksQuery query) {
    return bookRepository.findAll()
        .stream()
        .map((book) -> converter.toBookDTO(book))
        .toList();
  }
}