package com.quocchung.employee_service.service.impl;

import com.quocchung.common_service.response.ApiResponse;
import com.quocchung.employee_service.command.AddEmployeeCommand;
import com.quocchung.employee_service.command.RemoveEmployeeCommand;
import com.quocchung.employee_service.dtos.request.EmployeeRequestDTO;
import com.quocchung.employee_service.service.EmployeeCommandService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeCommandServiceImpl implements EmployeeCommandService {

  private final CommandGateway commandGateway;
  @Override
  public String addEmployee(EmployeeRequestDTO request) {
    String employeeId = UUID.randomUUID().toString();

    AddEmployeeCommand command = AddEmployeeCommand.builder()
        .employeeId(employeeId)
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .kin(request.getKin())
        .isDisciplined(request.getIsDisciplined())
        .build();

      commandGateway.sendAndWait(command);
      return "Employee added successfully"+  employeeId;
  }

  @Override
  public Boolean removeEmployee(String id) {
      RemoveEmployeeCommand command = RemoveEmployeeCommand.builder()
          .employeeId(id)
          .build();
        commandGateway.sendAndWait(command);
        return true;
  }
}
