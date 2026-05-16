package com.quocchung.employee_service.aggregate;

import com.quocchung.common_service.exception.ConflictException;
import com.quocchung.employee_service.command.AddEmployeeCommand;
import com.quocchung.employee_service.command.RemoveEmployeeCommand;
import com.quocchung.employee_service.event.EmployeeAddedEvent;
import com.quocchung.employee_service.event.EmployeeRemovedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Slf4j
public class EmployeeAggregate {

  @AggregateIdentifier
  private String employeeId;

  private String firstName;
  private String lastName;
  private String kin;
  private Boolean isDisciplined;
  private Boolean deleted = false;

  @CommandHandler
  public EmployeeAggregate(AddEmployeeCommand command) {

    AggregateLifecycle.apply(EmployeeAddedEvent.builder()
        .employeeId(command.getEmployeeId())
        .firstName(command.getFirstName())
        .lastName(command.getLastName())
        .kin(command.getKin())
        .isDisciplined(command.getIsDisciplined())
        .build());
  }

  @CommandHandler
  public void handle(RemoveEmployeeCommand command) {
    if (Boolean.TRUE.equals(this.deleted)) {
      throw new ConflictException(
          "Employee already deleted: " + command.getEmployeeId()
      );
    }
    AggregateLifecycle.apply(EmployeeRemovedEvent.builder()
        .employeeId(command.getEmployeeId())
        .build());
  }

  @EventSourcingHandler
  public void on(EmployeeAddedEvent event) {
    this.employeeId    = event.getEmployeeId();
    this.firstName     = event.getFirstName();
    this.lastName      = event.getLastName();
    this.kin           = event.getKin();
    this.isDisciplined = event.getIsDisciplined();
    this.deleted = false;

  }

  @EventSourcingHandler
  public void on(EmployeeRemovedEvent event) {
    this.deleted= true;
    AggregateLifecycle.markDeleted();

  }
}