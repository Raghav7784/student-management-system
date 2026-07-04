package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.dto.AllocationDTO;
import com.example.demo.entity.Allocation;
import com.example.demo.service.AllocationService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize(
        "hasRole('ADMIN') or hasRole('MANAGER')"
)
@RestController
@RequestMapping("/api/allocations")
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(
            AllocationService allocationService) {

        this.allocationService = allocationService;
    }

    @PostMapping
    public Allocation createAllocation(
            @RequestBody AllocationDTO dto) {

        return allocationService.createAllocation(dto);
    }

    @GetMapping
    public List<Allocation> getAllAllocations() {

        return allocationService.getAllAllocations();
    }

    @GetMapping("/{id}")
    public Allocation getAllocationById(
            @PathVariable Long id) {

        return allocationService.getAllocationById(id);
    }
    
    
    @PutMapping("/release/{id}")
    public Allocation releaseEmployee1(
            @PathVariable Long id) {

        return allocationService.releaseEmployee(id);
    }

    @PutMapping("/reallocate/{allocationId}/{projectId}")
    public Allocation reallocateEmployee(
            @PathVariable Long allocationId,
            @PathVariable Long projectId,
            @RequestParam Integer allocationPercentage) {

        return allocationService.reallocateEmployee(
                allocationId,
                projectId,
                allocationPercentage);
    }
    public Allocation releaseEmployee(
            @PathVariable Long id) {

        return allocationService.releaseEmployee(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAllocation(
            @PathVariable Long id) {

        allocationService.deleteAllocation(id);

        return "Allocation deleted successfully";
    }
}