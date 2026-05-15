package com.quocchung.book_service.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class BookRequest {
  private String id;
  private String name;

  private String author;

  private Boolean isReady;

}
