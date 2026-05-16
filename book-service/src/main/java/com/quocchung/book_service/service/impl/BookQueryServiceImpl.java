package com.quocchung.book_service.service.impl;

import com.quocchung.book_service.dtos.response.BookResponse;
import com.quocchung.book_service.query.FindAllBooksQuery;
import com.quocchung.book_service.query.FindBookByIdQuery;
import com.quocchung.book_service.service.BookQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookQueryServiceImpl implements BookQueryService {
  /**
   * queryGateway.query() trả về CompletableFuture<T>
   * bạn cần gọi join() hoặc get() để lấy kết quả.
   */
  private final QueryGateway queryGateway;


  @Override
  public BookResponse getBookById(String id) {
    return queryGateway.query(
            new FindBookByIdQuery(id),
            ResponseTypes.instanceOf(BookResponse.class)
        ).join();
  }

  @Override
  public List<BookResponse> getAllBooks() {
    return queryGateway.query(
            new FindAllBooksQuery(),
            ResponseTypes.multipleInstancesOf(BookResponse.class)
        ).join();

  }
}
