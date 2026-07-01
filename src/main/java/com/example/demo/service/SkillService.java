package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SkillRequestDTO;
import com.example.demo.dto.SkillResponseDTO;

public interface SkillService {

    SkillResponseDTO addSkill(SkillRequestDTO skillRequestDTO);

    SkillResponseDTO getSkillById(Long skillId);

    List<SkillResponseDTO> getAllSkills();

    SkillResponseDTO updateSkill(Long skillId, SkillRequestDTO skillRequestDTO);

    void deleteSkill(Long skillId);
}