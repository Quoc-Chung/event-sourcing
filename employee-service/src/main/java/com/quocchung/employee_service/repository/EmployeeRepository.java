package com.quocchung.employee_service.repository;

import com.quocchung.employee_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface EmployeeRepository extends
    JpaRepository<Employee, String>
    , JpaSpecificationExecutor<Employee> {
}
