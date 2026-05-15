package com.quocchung.book_service.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookCommand {
  @TargetAggregateIdentifier
  private String id;

  private String name;

  private String author;

  private Boolean isReady;
}
