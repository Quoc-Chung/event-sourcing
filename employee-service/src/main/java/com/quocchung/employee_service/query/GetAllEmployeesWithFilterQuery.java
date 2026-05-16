package com.quocchung.employee_service.query;

import com.quocchung.employee_service.dtos.request.EmployeeFilterRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllEmployeesWithFilterQuery {
  private EmployeeFilterRequest filter; 
}