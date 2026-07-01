package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SkillRequestDTO;
import com.example.demo.dto.SkillResponseDTO;
import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public SkillResponseDTO addSkill(SkillRequestDTO skillRequestDTO) {

        if (skillRepository.existsBySkillName(skillRequestDTO.getSkillName())) {
            throw new RuntimeException("Skill already exists");
        }

        Skill skill = new Skill();

        skill.setSkillName(skillRequestDTO.getSkillName());
        skill.setCategory(skillRequestDTO.getCategory());
        skill.setDescription(skillRequestDTO.getDescription());

        Skill savedSkill = skillRepository.save(skill);

        return mapToResponseDTO(savedSkill);
    }

    @Override
    public SkillResponseDTO getSkillById(Long skillId) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        return mapToResponseDTO(skill);
    }

    @Override
    public List<SkillResponseDTO> getAllSkills() {

        return skillRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SkillResponseDTO updateSkill(Long skillId, SkillRequestDTO skillRequestDTO) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        skill.setSkillName(skillRequestDTO.getSkillName());
        skill.setCategory(skillRequestDTO.getCategory());
        skill.setDescription(skillRequestDTO.getDescription());

        Skill updatedSkill = skillRepository.save(skill);

        return mapToResponseDTO(updatedSkill);
    }

    @Override
    public void deleteSkill(Long skillId) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        skillRepository.delete(skill);
    }

    private SkillResponseDTO mapToResponseDTO(Skill skill) {

        SkillResponseDTO dto = new SkillResponseDTO();

        dto.setSkillId(skill.getSkillId());
        dto.setSkillName(skill.getSkillName());
        dto.setCategory(skill.getCategory());
        dto.setDescription(skill.getDescription());

        return dto;
    }
}