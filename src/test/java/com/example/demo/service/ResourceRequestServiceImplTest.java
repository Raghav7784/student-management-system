package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import com.example.demo.dto.ResourceRequestDTO;
import com.example.demo.entity.Project;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.impl.ResourceRequestServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResourceRequestServiceImplTest {

    @Mock
    private ResourceRequestRepository requestRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ResourceRequestServiceImpl resourceRequestService;

    private Project project;
    private ResourceRequest request;
    private ResourceRequestDTO requestDTO;

    @BeforeEach
    void setUp() {

        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Employee Management System");

        request = new ResourceRequest();
        request.setRequestId(1L);
        request.setProject(project);
        request.setRequiredSkill("Java");
        request.setRequiredCount(3);
        request.setStatus("PENDING");

        requestDTO = new ResourceRequestDTO();
        requestDTO.setProjectId(1L);
        requestDTO.setRequiredSkill("Java");
        requestDTO.setRequiredCount(3);
        requestDTO.setStatus("PENDING");
    }

    @Test
    void testCreateRequestSuccess() {

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        when(requestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        ResourceRequest result =
                resourceRequestService.createRequest(requestDTO);

        assertNotNull(result);
        assertEquals("Java", result.getRequiredSkill());

        verify(requestRepository)
                .save(any(ResourceRequest.class));
    }

    @Test
    void testGetRequestByIdSuccess() {

        when(requestRepository.findById(1L))
                .thenReturn(Optional.of(request));

        ResourceRequest result =
                resourceRequestService.getRequestById(1L);

        assertEquals("Java", result.getRequiredSkill());
    }

    @Test
    void testGetRequestByIdNotFound() {

        when(requestRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> resourceRequestService.getRequestById(1L));
    }

    @Test
    void testGetAllRequests() {

        when(requestRepository.findAll())
                .thenReturn(Arrays.asList(request));

        assertEquals(
                1,
                resourceRequestService.getAllRequests().size());
    }

    @Test
    void testUpdateRequestSuccess() {

        when(requestRepository.findById(1L))
                .thenReturn(Optional.of(request));

        when(requestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        ResourceRequest result =
                resourceRequestService.updateRequest(
                        1L,
                        requestDTO);

        assertEquals(
                "Java",
                result.getRequiredSkill());

        verify(requestRepository)
                .save(any(ResourceRequest.class));
    }

    @Test
    void testDeleteRequestSuccess() {

        when(requestRepository.findById(1L))
                .thenReturn(Optional.of(request));

        resourceRequestService.deleteRequest(1L);

        verify(requestRepository)
                .delete(request);
    }

    @Test
    void testSubmitRequestSuccess() {

        when(requestRepository.findById(1L))
                .thenReturn(Optional.of(request));

        when(requestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        ResourceRequest result =
                resourceRequestService.submitRequest(1L);

        verify(requestRepository)
                .save(any(ResourceRequest.class));
    }

    @Test
    void testApproveRequestSuccess() {

        when(requestRepository.findById(1L))
                .thenReturn(Optional.of(request));

        when(requestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        ResourceRequest result =
                resourceRequestService.approveRequest(1L);

        verify(requestRepository)
                .save(any(ResourceRequest.class));
    }

    @Test
    void testAllocateRequestSuccess() {

        when(requestRepository.findById(1L))
                .thenReturn(Optional.of(request));

        when(requestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        ResourceRequest result =
                resourceRequestService.allocateRequest(1L);

        verify(requestRepository)
                .save(any(ResourceRequest.class));
    }

    @Test
    void testCompleteRequestSuccess() {

        when(requestRepository.findById(1L))
                .thenReturn(Optional.of(request));

        when(requestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        ResourceRequest result =
                resourceRequestService.completeRequest(1L);

        verify(requestRepository)
                .save(any(ResourceRequest.class));
    }
}