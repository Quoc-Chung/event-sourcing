package com.quocchung.book_service.command;


import org.axonframework.modelling.command.TargetAggregateIdentifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBookCommand {
  @TargetAggregateIdentifier
  private String bookId;
  private String name;
  private String author;
  private Boolean isReady;
}
