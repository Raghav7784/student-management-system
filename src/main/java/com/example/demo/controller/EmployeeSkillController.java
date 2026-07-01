package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.EmployeeSkillRequestDTO;
import com.example.demo.dto.EmployeeSkillResponseDTO;
import com.example.demo.service.EmployeeSkillService;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final EmployeeSkillService employeeSkillService;

    public EmployeeSkillController(EmployeeSkillService employeeSkillService) {
        this.employeeSkillService = employeeSkillService;
    }

    // Add Employee Skill
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeSkillResponseDTO addEmployeeSkill(
            @RequestBody EmployeeSkillRequestDTO employeeSkillRequestDTO) {

        return employeeSkillService.addEmployeeSkill(employeeSkillRequestDTO);
    }

    // Get Employee Skill By ID
    @GetMapping("/{id}")
    public EmployeeSkillResponseDTO getEmployeeSkillById(@PathVariable Long id) {

        return employeeSkillService.getEmployeeSkillById(id);
    }

    // Get All Employee Skills
    @GetMapping
    public List<EmployeeSkillResponseDTO> getAllEmployeeSkills() {

        return employeeSkillService.getAllEmployeeSkills();
    }

    // Update Employee Skill
    @PutMapping("/{id}")
    public EmployeeSkillResponseDTO updateEmployeeSkill(
            @PathVariable Long id,
            @RequestBody EmployeeSkillRequestDTO employeeSkillRequestDTO) {

        return employeeSkillService.updateEmployeeSkill(id, employeeSkillRequestDTO);
    }

    // Delete Employee Skill
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeSkill(@PathVariable Long id) {

        employeeSkillService.deleteEmployeeSkill(id);
    }
}