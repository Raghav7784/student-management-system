package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Certification;
import com.example.demo.entity.Employee;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    List<Certification> findByEmployee(Employee employee);

}