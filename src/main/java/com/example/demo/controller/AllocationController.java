package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.dto.AllocationDTO;
import com.example.demo.entity.Allocation;
import com.example.demo.service.AllocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize(
	    "hasRole('ADMIN') or hasRole('MANAGER')"
	)
	@RestController
	@RequestMapping("/api/allocations")
	public class AllocationController{

    @Autowired
    private AllocationService allocationService;

    @PostMapping
    public Allocation createAllocation(
            @RequestBody AllocationDTO dto) {

        return allocationService
                .createAllocation(dto);
    }

    @GetMapping
    public List<Allocation> getAllAllocations() {

        return allocationService
                .getAllAllocations();
    }

    @GetMapping("/{id}")
    public Allocation getAllocationById(
            @PathVariable Long id) {

        return allocationService
                .getAllocationById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAllocation(
            @PathVariable Long id) {

        allocationService.deleteAllocation(id);

        return "Allocation deleted successfully";
    }
}