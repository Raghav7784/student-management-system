package com.example.demo.service;

import com.example.demo.dto.AllocationDTO;
import com.example.demo.entity.Allocation;

import java.util.List;

public interface AllocationService {

    Allocation createAllocation(AllocationDTO dto);

    List<Allocation> getAllAllocations();

    Allocation getAllocationById(Long id);

    void deleteAllocation(Long id);
}