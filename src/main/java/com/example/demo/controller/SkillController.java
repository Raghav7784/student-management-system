package com.example.demo.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.SkillRequestDTO;
import com.example.demo.dto.SkillResponseDTO;
import com.example.demo.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillResponseDTO addSkill(@RequestBody SkillRequestDTO skillRequestDTO) {
        return skillService.addSkill(skillRequestDTO);
    }

    @GetMapping("/{id}")
    public SkillResponseDTO getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @GetMapping
    public List<SkillResponseDTO> getAllSkills() {
        return skillService.getAllSkills();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public SkillResponseDTO updateSkill(@PathVariable Long id,
                                        @RequestBody SkillRequestDTO skillRequestDTO) {
        return skillService.updateSkill(id, skillRequestDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}