package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SkillRequestDTO;
import com.example.demo.dto.SkillResponseDTO;
import com.example.demo.entity.Skill;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

    private static final Logger logger =
            LoggerFactory.getLogger(SkillServiceImpl.class);

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public SkillResponseDTO addSkill(SkillRequestDTO skillRequestDTO) {

        logger.info("Adding skill: {}",
                skillRequestDTO.getSkillName());

        if (skillRepository.existsBySkillName(
                skillRequestDTO.getSkillName())) {

            logger.warn("Skill already exists: {}",
                    skillRequestDTO.getSkillName());

            throw new RuntimeException("Skill already exists");
        }

        Skill skill = new Skill();

        skill.setSkillName(skillRequestDTO.getSkillName());
        skill.setCategory(skillRequestDTO.getCategory());
        skill.setDescription(skillRequestDTO.getDescription());

        Skill savedSkill = skillRepository.save(skill);

        logger.info("Skill created successfully with ID: {}",
                savedSkill.getSkillId());

        return mapToResponseDTO(savedSkill);
    }

    @Override
    public SkillResponseDTO getSkillById(Long skillId) {

        logger.info("Fetching skill with ID: {}", skillId);

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> {

                    logger.error("Skill not found with ID: {}",
                            skillId);

                    return new ResourceNotFoundException(
                            "Skill not found");
                });

        return mapToResponseDTO(skill);
    }

    @Override
    public List<SkillResponseDTO> getAllSkills() {

        logger.info("Fetching all skills");

        return skillRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SkillResponseDTO updateSkill(
            Long skillId,
            SkillRequestDTO skillRequestDTO) {

        logger.info("Updating skill with ID: {}", skillId);

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> {

                    logger.error("Skill not found with ID: {}",
                            skillId);

                    return new ResourceNotFoundException(
                            "Skill not found");
                });

        skill.setSkillName(skillRequestDTO.getSkillName());
        skill.setCategory(skillRequestDTO.getCategory());
        skill.setDescription(skillRequestDTO.getDescription());

        Skill updatedSkill = skillRepository.save(skill);

        logger.info("Skill updated successfully: {}",
                updatedSkill.getSkillName());

        return mapToResponseDTO(updatedSkill);
    }

    @Override
    public void deleteSkill(Long skillId) {

        logger.info("Deleting skill with ID: {}", skillId);

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> {

                    logger.error("Skill not found with ID: {}",
                            skillId);

                    return new ResourceNotFoundException(
                            "Skill not found");
                });

        skillRepository.delete(skill);

        logger.info("Skill deleted successfully with ID: {}",
                skillId);
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