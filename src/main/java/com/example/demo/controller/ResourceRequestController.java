package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import com.example.demo.dto.ResourceRequestDTO;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.service.ResourceRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('MANAGER')")
@RestController
@RequestMapping("/api/resource-requests")
public class ResourceRequestController {

    @Autowired
    private ResourceRequestService resourceRequestService;

    @PostMapping
    public ResourceRequest createRequest(
            @RequestBody ResourceRequestDTO dto) {

        return resourceRequestService.createRequest(dto);
    }

    @GetMapping
    public List<ResourceRequest> getAllRequests() {

        return resourceRequestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public ResourceRequest getRequestById(
            @PathVariable Long id) {

        return resourceRequestService.getRequestById(id);
    }

    @PutMapping("/{id}")
    public ResourceRequest updateRequest(
            @PathVariable Long id,
            @RequestBody ResourceRequestDTO dto) {

        return resourceRequestService.updateRequest(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteRequest(
            @PathVariable Long id) {

        resourceRequestService.deleteRequest(id);

        return "Resource Request Deleted Successfully";
    }

    @PutMapping("/{id}/submit")
    public ResourceRequest submitRequest(
            @PathVariable Long id) {

        return resourceRequestService.submitRequest(id);
    }

    @PutMapping("/{id}/approve")
    public ResourceRequest approveRequest(
            @PathVariable Long id) {

        return resourceRequestService.approveRequest(id);
    }

    @PutMapping("/{id}/allocate")
    public ResourceRequest allocateRequest(
            @PathVariable Long id) {

        return resourceRequestService.allocateRequest(id);
    }

    @PutMapping("/{id}/complete")
    public ResourceRequest completeRequest(
            @PathVariable Long id) {

        return resourceRequestService.completeRequest(id);
    }
}