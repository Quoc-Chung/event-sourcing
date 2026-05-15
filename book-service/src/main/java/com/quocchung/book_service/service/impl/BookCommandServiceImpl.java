package com.quocchung.book_service.service.impl;

import com.quocchung.book_service.command.CreateBookCommand;
import com.quocchung.book_service.command.DeleteBookComand;
import com.quocchung.book_service.command.UpdateBookCommand;
import com.quocchung.book_service.dtos.requests.BookRequest;
import com.quocchung.book_service.service.BookCommandService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCommandServiceImpl implements BookCommandService {

  private final CommandGateway commandGateway;

  @Override
  public String createBook(BookRequest request) {
    String bookId = UUID.randomUUID().toString();
    CreateBookCommand createBookCommand = CreateBookCommand.
        builder()
        .id(bookId).
        name(request.getName())
        .author(request.getAuthor())
        .isReady(request.getIsReady()).build();
    return commandGateway.sendAndWait(createBookCommand);
  }

  @Override
  public void updateBook(String id, BookRequest request) {
    UpdateBookCommand updateBookCommand = UpdateBookCommand
        .builder()
        .bookId(id)
        .name(request.getName())
        .author(request.getAuthor())
        .isReady(request.getIsReady())
        .build();
    commandGateway.sendAndWait(updateBookCommand);
  }

  @Override
  public void deleteBook(String id) {
    commandGateway.sendAndWait(new DeleteBookComand(id));
  }
}
