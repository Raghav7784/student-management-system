package com.example.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResourceRequestDTO;
import com.example.demo.entity.Project;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceRequestService;

@Service
public class ResourceRequestServiceImpl
        implements ResourceRequestService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    ResourceRequestServiceImpl.class);

    private final ResourceRequestRepository requestRepository;
    private final ProjectRepository projectRepository;

    public ResourceRequestServiceImpl(
            ResourceRequestRepository requestRepository,
            ProjectRepository projectRepository) {

        this.requestRepository = requestRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public ResourceRequest createRequest(
            ResourceRequestDTO dto) {

        logger.info(
                "Creating resource request for project ID: {}",
                dto.getProjectId());

        Project project = projectRepository
                .findById(dto.getProjectId())
                .orElseThrow(() -> {

                    logger.error(
                            "Project not found with ID: {}",
                            dto.getProjectId());

                    return new ResourceNotFoundException(
                            "Project not found");
                });

        ResourceRequest request = new ResourceRequest();

        request.setProject(project);
        request.setRequiredSkill(dto.getRequiredSkill());
        request.setRequiredCount(dto.getRequiredCount());
        request.setStatus(dto.getStatus());

        ResourceRequest saved =
                requestRepository.save(request);

        logger.info(
                "Resource request created successfully for skill: {}",
                dto.getRequiredSkill());

        return saved;
    }

    @Override
    public List<ResourceRequest> getAllRequests() {

        logger.info("Fetching all resource requests");

        return requestRepository.findAll();
    }

    @Override
    public ResourceRequest getRequestById(Long id) {

        logger.info(
                "Fetching resource request with ID: {}",
                id);

        return requestRepository.findById(id)
                .orElseThrow(() -> {

                    logger.error(
                            "Resource request not found with ID: {}",
                            id);

                    return new ResourceNotFoundException(
                            "Request not found");
                });
    }

    @Override
    public ResourceRequest updateRequest(
            Long id,
            ResourceRequestDTO dto) {

        logger.info(
                "Updating resource request with ID: {}",
                id);

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() -> {

                            logger.error(
                                    "Request not found with ID: {}",
                                    id);

                            return new ResourceNotFoundException(
                                    "Request not found");
                        });

        request.setRequiredSkill(
                dto.getRequiredSkill());

        request.setRequiredCount(
                dto.getRequiredCount());

        request.setStatus(
                dto.getStatus());

        ResourceRequest updated =
                requestRepository.save(request);

        logger.info(
                "Resource request updated successfully with ID: {}",
                id);

        return updated;
    }

    @Override
    public void deleteRequest(Long id) {

        logger.info(
                "Deleting resource request with ID: {}",
                id);

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() -> {

                            logger.error(
                                    "Request not found with ID: {}",
                                    id);

                            return new ResourceNotFoundException(
                                    "Request not found");
                        });

        requestRepository.delete(request);

        logger.info(
                "Resource request deleted successfully with ID: {}",
                id);
    }

    @Override
    public ResourceRequest submitRequest(Long id) {

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Request not found"));

        request.setStatus("SUBMITTED");

        logger.info(
                "Resource request submitted with ID: {}",
                id);

        return requestRepository.save(request);
    }

    @Override
    public ResourceRequest approveRequest(Long id) {

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Request not found"));

        request.setStatus("APPROVED");

        logger.info(
                "Resource request approved with ID: {}",
                id);

        return requestRepository.save(request);
    }

    @Override
    public ResourceRequest allocateRequest(Long id) {

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Request not found"));

        request.setStatus("ALLOCATED");

        logger.info(
                "Resource request allocated with ID: {}",
                id);

        return requestRepository.save(request);
    }

    @Override
    public ResourceRequest completeRequest(Long id) {

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Request not found"));

        request.setStatus("COMPLETED");

        logger.info(
                "Resource request completed with ID: {}",
                id);

        return requestRepository.save(request);
    }
}