package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeSkillRequestDTO;
import com.example.demo.dto.EmployeeSkillResponseDTO;

public interface EmployeeSkillService {

    EmployeeSkillResponseDTO addEmployeeSkill(EmployeeSkillRequestDTO employeeSkillRequestDTO);

    EmployeeSkillResponseDTO getEmployeeSkillById(Long employeeSkillId);

    List<EmployeeSkillResponseDTO> getAllEmployeeSkills();

    EmployeeSkillResponseDTO updateEmployeeSkill(Long employeeSkillId,
                                                 EmployeeSkillRequestDTO employeeSkillRequestDTO);

    void deleteEmployeeSkill(Long employeeSkillId);

}