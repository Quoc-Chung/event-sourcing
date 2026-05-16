package com.quocchung.employee_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAddedEvent {

  private String employeeId;
  private String firstName;
  private String lastName;
  private String kin;
  private Boolean isDisciplined;
}

