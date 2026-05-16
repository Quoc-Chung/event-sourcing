package com.quocchung.employee_service.command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeCommand {

  @TargetAggregateIdentifier
  private String employeeId;

  private String firstName;
  private String lastName;
  private String kin;
  private Boolean isDisciplined;
}