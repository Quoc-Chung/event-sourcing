package com.quocchung.employee_service.service;

import com.quocchung.employee_service.dtos.request.EmployeeFilterRequest;
import com.quocchung.employee_service.dtos.response.EmployeeResponseDTO;
import java.util.List;

public interface EmployeeQueryService {

  EmployeeResponseDTO getEmployeeDetails(String id);

  List<EmployeeResponseDTO> getAllEmployeesWithFilter(EmployeeFilterRequest filter);
}
