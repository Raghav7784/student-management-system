package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ResourceRequest;

public interface ResourceRequestRepository
        extends JpaRepository<ResourceRequest, Long> {

}