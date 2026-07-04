package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditLog;
import com.example.demo.repository.AuditLogRepository;
import com.example.demo.service.AuditLogService;

@Service
public class AuditLogServiceImpl
        implements AuditLogService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    AuditLogServiceImpl.class);

    private final AuditLogRepository auditLogRepository;

    public AuditLogServiceImpl(
            AuditLogRepository auditLogRepository) {

        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public void saveLog(String action,
                        String performedBy) {

        logger.info(
                "Audit Log - Action: {}, Performed By: {}",
                action,
                performedBy);

        AuditLog log = new AuditLog();

        log.setAction(action);
        log.setPerformedBy(performedBy);
        log.setActionTime(LocalDateTime.now());

        auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> getAllLogs() {

        logger.info("Fetching all audit logs");

        return auditLogRepository.findAll();
    }
}