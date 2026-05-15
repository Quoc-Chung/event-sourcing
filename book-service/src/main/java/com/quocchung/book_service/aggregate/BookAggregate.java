package com.quocchung.book_service.aggregate;

import com.quocchung.book_service.command.CreateBookCommand;
import com.quocchung.book_service.command.DeleteBookComand;
import com.quocchung.book_service.command.UpdateBookCommand;
import com.quocchung.book_service.event.BookCreatedEvent;
import com.quocchung.book_service.event.BookDeletedEvent;
import com.quocchung.book_service.event.BookUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookAggregate {
  @AggregateIdentifier
  private String bookId;

  private Boolean isReady;

  private boolean deleted;

  /**
   * COMMAND HANDLE
   */
  @CommandHandler
  public BookAggregate(CreateBookCommand command){
    if (command.getName() == null || command.getName().isBlank())
      throw new IllegalArgumentException("Tên sách không được trống");

    if (command.getAuthor() == null || command.getAuthor().isBlank())
      throw new IllegalArgumentException("Tác giả không được trống");

    BookCreatedEvent bookCreateEvent = new BookCreatedEvent(
        command.getId(),
        command.getName(),
        command.getAuthor(),
        command.getIsReady() != null ? command.getIsReady() : false);

    AggregateLifecycle.apply(bookCreateEvent);
  }
  @CommandHandler
  public void handle(UpdateBookCommand command) {
    // validate dựa trên state
    if (this.deleted)
      throw new IllegalStateException("Sách đã bị xóa, không thể cập nhật");

    if (command.getName() == null || command.getName().isBlank())
      throw new IllegalArgumentException("Tên sách không được trống");

    if (command.getAuthor() == null || command.getAuthor().isBlank())
      throw new IllegalArgumentException("Tác giả không được trống");

    // sinh event
    AggregateLifecycle.apply(new BookUpdatedEvent(
        command.getBookId(),
        command.getName(),
        command.getAuthor(),
        command.getIsReady()
    ));
  }
  @CommandHandler
  public void handle(DeleteBookComand command) {
    // validate dựa trên state
    if (this.deleted)
      throw new IllegalStateException("Sách đã bị xóa rồi");

    // sinh event
    AggregateLifecycle.apply(new BookDeletedEvent(command.getBookId()));
  }

  /**
   *  EVENT SOURCING HANDLE
   */
  @EventSourcingHandler
  public void on(BookCreatedEvent event) {
    this.bookId  = event.getBookId();
    this.isReady = event.getIsReady();
    this.deleted = false;
  }

  @EventSourcingHandler
  public void on(BookUpdatedEvent event) {
    this.isReady = event.getIsReady();
  }

  @EventSourcingHandler
  public void on(BookDeletedEvent event) {
    this.deleted = true;
    AggregateLifecycle.markDeleted();
  }


}
