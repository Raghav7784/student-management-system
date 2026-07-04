package com.example.demo.service;

import com.example.demo.dto.AllocationDTO;
import com.example.demo.entity.Allocation;

import java.util.List;

public interface AllocationService {

    Allocation createAllocation(AllocationDTO dto);

    List<Allocation> getAllAllocations();

    Allocation getAllocationById(Long id);

    Allocation releaseEmployee(Long allocationId);

    Allocation reallocateEmployee(
            Long allocationId,
            Long newProjectId,
            Integer allocationPercentage);

    void deleteAllocation(Long id);
}