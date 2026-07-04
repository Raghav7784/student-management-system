package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentDepartmentName(
            String departmentName);

    Optional<Employee> findByPhone(String phone);

    Page<Employee> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(
            String firstName,
            String lastName,
            Pageable pageable);
}