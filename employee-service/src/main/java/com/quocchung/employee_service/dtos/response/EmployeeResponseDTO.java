package com.quocchung.employee_service.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponseDTO {
  private String       id;
  private String       firstName;
  private String       lastName;
  private String       kin;
  private Boolean      isDisciplined;
  private List<String> borrowedBookIds;
}
