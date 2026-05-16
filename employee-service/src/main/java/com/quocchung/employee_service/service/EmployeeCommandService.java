package com.quocchung.employee_service.service;

import com.quocchung.employee_service.dtos.request.EmployeeRequestDTO;

public interface EmployeeCommandService {

  String addEmployee(EmployeeRequestDTO request);

  Boolean removeEmployee(String id);

}
