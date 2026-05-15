package com.quocchung.book_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="books")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  @Id
  private String id;

  private String name;

  private String author;

  private Boolean isReady;

  @Version
  private Long version;
}
