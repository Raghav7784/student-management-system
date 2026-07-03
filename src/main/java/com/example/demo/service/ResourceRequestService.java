package com.example.demo.service;

import com.example.demo.dto.ResourceRequestDTO;
import com.example.demo.entity.ResourceRequest;

import java.util.List;

public interface ResourceRequestService {

    ResourceRequest createRequest(ResourceRequestDTO dto);

    List<ResourceRequest> getAllRequests();

    ResourceRequest getRequestById(Long id);

    ResourceRequest updateRequest(Long id,
                                  ResourceRequestDTO dto);

    void deleteRequest(Long id);

    ResourceRequest submitRequest(Long id);

    ResourceRequest approveRequest(Long id);

    ResourceRequest allocateRequest(Long id);

    ResourceRequest completeRequest(Long id);

}