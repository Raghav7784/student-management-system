package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditLog;
import com.example.demo.repository.AuditLogRepository;
import com.example.demo.service.AuditLogService;

@Service
public class AuditLogServiceImpl
        implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public void saveLog(String action,
                        String performedBy) {

        AuditLog log = new AuditLog();

        log.setAction(action);
        log.setPerformedBy(performedBy);
        log.setActionTime(LocalDateTime.now());

        auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> getAllLogs() {

        return auditLogRepository.findAll();
    }
}