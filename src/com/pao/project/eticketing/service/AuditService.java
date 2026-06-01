package com.pao.project.eticketing.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AuditService {
    private static final AuditService INSTANCE = new AuditService();
    private static final String FILE_PATH = "audit.csv";

    private AuditService() {}

    public static AuditService getInstance() {
        return INSTANCE;
    }

    public synchronized void logAction(String actionName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(actionName + "," + LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}