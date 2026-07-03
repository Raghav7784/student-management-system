package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AuditLog;

public interface AuditLogService {

    void saveLog(String action,
                 String performedBy);

    List<AuditLog> getAllLogs();

}