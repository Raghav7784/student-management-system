package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dto.ResourceRequestDTO;
import com.example.demo.entity.Project;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.AuditLogService;
import com.example.demo.service.ResourceRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceRequestServiceImpl implements ResourceRequestService {
	
	
	private static final Logger logger =
	        LoggerFactory.getLogger(
	                ResourceRequestServiceImpl.class);
	@Autowired
	private AuditLogService auditLogService;

    @Autowired
    private ResourceRequestRepository requestRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ResourceRequest createRequest(ResourceRequestDTO dto) {

    	
    	logger.info(
    	        "Resource request created for skill: {}",
    	        dto.getRequiredSkill());
        Project project = projectRepository
                .findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        ResourceRequest request = new ResourceRequest();

        request.setProject(project);
        request.setRequiredSkill(dto.getRequiredSkill());
        request.setRequiredCount(dto.getRequiredCount());
        request.setStatus(dto.getStatus());

        return requestRepository.save(request);
    }

    @Override
    public List<ResourceRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public ResourceRequest getRequestById(Long id) {
    	
    	logger.warn(
    	        "Resource Request not found: {}",
    	        id);

        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @Override
    public ResourceRequest updateRequest(Long id,
                                         ResourceRequestDTO dto) {

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found"));

        request.setRequiredSkill(dto.getRequiredSkill());
        request.setRequiredCount(dto.getRequiredCount());
        request.setStatus(dto.getStatus());

        return requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Long id) {

        requestRepository.deleteById(id);
    }

    @Override
    public ResourceRequest submitRequest(Long id) {

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found"));

        request.setStatus("SUBMITTED");

        return requestRepository.save(request);
    }

    @Override
    public ResourceRequest approveRequest(Long id) {

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found"));

        request.setStatus("APPROVED");

        return requestRepository.save(request);
    }

    @Override
    public ResourceRequest allocateRequest(Long id) {

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found"));

        request.setStatus("ALLOCATED");

        return requestRepository.save(request);
    }

    @Override
    public ResourceRequest completeRequest(Long id) {

        ResourceRequest request =
                requestRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found"));

        request.setStatus("COMPLETED");

        return requestRepository.save(request);
    }
}