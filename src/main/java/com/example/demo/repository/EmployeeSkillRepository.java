package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeSkill;
import com.example.demo.entity.Skill;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    List<EmployeeSkill> findByEmployee(Employee employee);
    
    List<EmployeeSkill> findBySkill(Skill skill);

}
