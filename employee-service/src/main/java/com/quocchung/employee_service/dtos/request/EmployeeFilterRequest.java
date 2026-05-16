package com.quocchung.employee_service.dtos.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFilterRequest {

  private String  firstName;
  private String  lastName;
  private String  kin;
  private Boolean isDisciplined;
}

