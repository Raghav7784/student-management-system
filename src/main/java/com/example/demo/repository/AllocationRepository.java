package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Allocation;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    List<Allocation> findByEmployee(Employee employee);
    
    List<Allocation> findByProject(Project project);

}