package com.quocchung.employee_service.service.impl;

import com.quocchung.employee_service.dtos.request.EmployeeFilterRequest;
import com.quocchung.employee_service.dtos.response.EmployeeResponseDTO;
import com.quocchung.employee_service.query.GetAllEmployeesWithFilterQuery;
import com.quocchung.employee_service.query.GetEmployeeDetailsQuery;
import com.quocchung.employee_service.service.EmployeeQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeQueryServiceImpl implements EmployeeQueryService{
  private final QueryGateway queryGateway;

  @Override
  public EmployeeResponseDTO getEmployeeDetails(String id) {
    GetEmployeeDetailsQuery query = new GetEmployeeDetailsQuery(id);
    EmployeeResponseDTO employee = queryGateway
        .query(query, ResponseTypes.instanceOf(EmployeeResponseDTO.class))
        .join();
    return employee;
  }

  @Override
  public List<EmployeeResponseDTO> getAllEmployeesWithFilter(EmployeeFilterRequest filter) {
    GetAllEmployeesWithFilterQuery query = new GetAllEmployeesWithFilterQuery(filter);
    return queryGateway
        .query(query, ResponseTypes.multipleInstancesOf(EmployeeResponseDTO.class))
        .join();
  }


}
