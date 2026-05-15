package com.quocchung.book_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdatedEvent {

  private String bookId;
  private String name;
  private String author;
  private Boolean isReady;
}