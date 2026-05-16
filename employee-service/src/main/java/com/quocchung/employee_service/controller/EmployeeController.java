package com.quocchung.employee_service.controller;

import com.quocchung.common_service.response.ApiResponse;
import com.quocchung.employee_service.dtos.request.EmployeeFilterRequest;
import com.quocchung.employee_service.dtos.request.EmployeeRequestDTO;
import com.quocchung.employee_service.dtos.response.EmployeeResponseDTO;
import com.quocchung.employee_service.service.EmployeeCommandService;
import com.quocchung.employee_service.service.EmployeeQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
  private final EmployeeCommandService employeeCommandService;
  private final EmployeeQueryService employeeQueryService;
  /**
   * POST /api/v1/employees
   * Tạo mới nhân viên.
   */
  @PostMapping
  public ResponseEntity<ApiResponse<String>> addEmployee(
      @RequestBody EmployeeRequestDTO request) {
   String response = employeeCommandService.addEmployee(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
  }

  /**
   * DELETE /api/v1/employees/{id}
   * Xoá nhân viên theo ID.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Boolean>> removeEmployee(
      @PathVariable String id) {
    Boolean response = employeeCommandService.removeEmployee(id);
    return ResponseEntity.ok(ApiResponse.success(response));
  }
  /**
   * GET /api/v1/employees/{id}
   * Lấy thông tin chi tiết nhân viên.
   */
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getEmployeeDetails(
      @PathVariable String id) {
   EmployeeResponseDTO response = employeeQueryService.getEmployeeDetails(id);
    return ResponseEntity.ok( ApiResponse.success(response));
  }


  @GetMapping("/all")
  public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getEmployeeAllWithFilter(
      EmployeeFilterRequest filter
      ) {
    List<EmployeeResponseDTO> response = employeeQueryService.getAllEmployeesWithFilter(filter);
    return ResponseEntity.ok( ApiResponse.success(response));
  }

}
