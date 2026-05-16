package com.quocchung.employee_service.utils;


import com.quocchung.employee_service.dtos.request.EmployeeFilterRequest;
import com.quocchung.employee_service.model.Employee;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

  private EmployeeSpecification() {}

  public static Specification<Employee> withFilter(EmployeeFilterRequest filter) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (filter == null) {
        return cb.conjunction();
      }

      if (filter.getFirstName() != null && !filter.getFirstName().isBlank()) {
        predicates.add(cb.like(
            cb.lower(root.get("firstName")),
            "%" + filter.getFirstName().toLowerCase() + "%"
        ));
      }

      if (filter.getLastName() != null && !filter.getLastName().isBlank()) {
        predicates.add(cb.like(
            cb.lower(root.get("lastName")),
            "%" + filter.getLastName().toLowerCase() + "%"
        ));
      }

      if (filter.getKin() != null && !filter.getKin().isBlank()) {
        predicates.add(cb.equal(
            cb.lower(root.get("kin")),
            filter.getKin().toLowerCase()
        ));
      }

      if (filter.getIsDisciplined() != null) {
        predicates.add(cb.equal(
            root.get("isDisciplined"),
            filter.getIsDisciplined()
        ));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }
}