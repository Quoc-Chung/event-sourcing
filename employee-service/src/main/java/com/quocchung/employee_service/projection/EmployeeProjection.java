package com.quocchung.employee_service.projection;

import com.quocchung.common_service.exception.NotFoundException;
import com.quocchung.employee_service.dtos.response.EmployeeResponseDTO;
import com.quocchung.employee_service.event.EmployeeAddedEvent;
import com.quocchung.employee_service.event.EmployeeRemovedEvent;
import com.quocchung.employee_service.model.Employee;
import com.quocchung.employee_service.query.GetAllEmployeesWithFilterQuery;
import com.quocchung.employee_service.query.GetEmployeeDetailsQuery;
import com.quocchung.employee_service.repository.EmployeeRepository;
import com.quocchung.employee_service.utils.EmployeeSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeProjection {

  private final EmployeeRepository employeeRepository;


  @EventHandler
  public void on(EmployeeAddedEvent event) {
    Employee employee = Employee.builder()
        .id(event.getEmployeeId())
        .firstName(event.getFirstName())
        .lastName(event.getLastName())
        .kin(event.getKin())
        .isDisciplined(event.getIsDisciplined())
        .build();

    employeeRepository.save(employee);
  }

    @EventHandler
    public void on(EmployeeRemovedEvent event) {

      employeeRepository.findById(event.getEmployeeId())
          .ifPresent(employeeRepository::delete);
    }


  @QueryHandler
  public EmployeeResponseDTO handle(GetEmployeeDetailsQuery query) {

    Employee employee = employeeRepository.findById(query.getEmployeeId())
        .orElseThrow(() -> new NotFoundException(
            "Employee not found with id: " +  query.getEmployeeId()
        ));

    return EmployeeResponseDTO.builder()
        .id(employee.getId())
        .firstName(employee.getFirstName())
        .lastName(employee.getLastName())
        .kin(employee.getKin())
        .isDisciplined(employee.getIsDisciplined())
        .build();
  }


  @QueryHandler
  public List<EmployeeResponseDTO> handle(GetAllEmployeesWithFilterQuery query) {
    Specification<Employee> spec = EmployeeSpecification.withFilter(query.getFilter());

    return employeeRepository.findAll(spec)
        .stream()
        .map(e -> EmployeeResponseDTO.builder()
            .id(e.getId())
            .firstName(e.getFirstName())
            .lastName(e.getLastName())
            .kin(e.getKin())
            .isDisciplined(e.getIsDisciplined())
            .build())
        .toList();
  }
}
