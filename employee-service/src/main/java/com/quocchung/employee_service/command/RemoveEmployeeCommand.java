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
public class RemoveEmployeeCommand {

  @TargetAggregateIdentifier
  private String employeeId;
}
