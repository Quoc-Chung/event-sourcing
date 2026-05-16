package com.quocchung.employee_service.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
  @Id
  private String id;

  private String firstName;

  private String lastName;

  private String kin;

  private Boolean isDisciplined;
}
