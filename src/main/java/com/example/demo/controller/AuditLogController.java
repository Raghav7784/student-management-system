package com.example.demo.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuditLog;
import com.example.demo.service.AuditLogService;

@PreAuthorize(
	    "hasRole('ADMIN') or hasRole('AUDITOR')"
	)
@RestController
@RequestMapping("/api/audit-logs")
@CrossOrigin("*")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public List<AuditLog> getAllLogs() {

        return auditLogService.getAllLogs();
    }
}