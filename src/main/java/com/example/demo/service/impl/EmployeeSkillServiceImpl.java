package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeSkillRequestDTO;
import com.example.demo.dto.EmployeeSkillResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeSkill;
import com.example.demo.entity.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {

        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkillResponseDTO addEmployeeSkill(EmployeeSkillRequestDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        EmployeeSkill employeeSkill = new EmployeeSkill();

        employeeSkill.setEmployee(employee);
        employeeSkill.setSkill(skill);
        employeeSkill.setSkillLevel(dto.getSkillLevel());
        employeeSkill.setYearsOfExperience(dto.getYearsOfExperience());

        EmployeeSkill saved = employeeSkillRepository.save(employeeSkill);

        return mapToDTO(saved);
    }

    @Override
    public EmployeeSkillResponseDTO getEmployeeSkillById(Long id) {

        EmployeeSkill employeeSkill = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Skill not found"));

        return mapToDTO(employeeSkill);
    }

    @Override
    public List<EmployeeSkillResponseDTO> getAllEmployeeSkills() {

        return employeeSkillRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeSkillResponseDTO updateEmployeeSkill(Long id, EmployeeSkillRequestDTO dto) {

        EmployeeSkill employeeSkill = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Skill not found"));

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        employeeSkill.setEmployee(employee);
        employeeSkill.setSkill(skill);
        employeeSkill.setSkillLevel(dto.getSkillLevel());
        employeeSkill.setYearsOfExperience(dto.getYearsOfExperience());

        EmployeeSkill updated = employeeSkillRepository.save(employeeSkill);

        return mapToDTO(updated);
    }

    @Override
    public void deleteEmployeeSkill(Long id) {

        EmployeeSkill employeeSkill = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Skill not found"));

        employeeSkillRepository.delete(employeeSkill);
    }

    private EmployeeSkillResponseDTO mapToDTO(EmployeeSkill employeeSkill) {

        EmployeeSkillResponseDTO dto = new EmployeeSkillResponseDTO();

        dto.setEmployeeSkillId(employeeSkill.getEmployeeSkillId());

        dto.setEmployeeName(
                employeeSkill.getEmployee().getFirstname() + " "
                        + employeeSkill.getEmployee().getLastname());

        dto.setSkillName(employeeSkill.getSkill().getSkillName());

        dto.setSkillLevel(employeeSkill.getSkillLevel());

        dto.setYearsOfExperience(employeeSkill.getYearsOfExperience());

        return dto;
    }

}