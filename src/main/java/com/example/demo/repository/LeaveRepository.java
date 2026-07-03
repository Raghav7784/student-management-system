package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

	List<Leave> findByEmployeeEmployeeId(Long employeeId);

}