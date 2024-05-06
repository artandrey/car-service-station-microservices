package com.example.management_service.modules.user.exceptions;

import com.example.management_service.shared.exceptions.ClientException;

public class WorkerPositionNotFoundException extends ClientException {
    public WorkerPositionNotFoundException(Long workerPositionId) {
        super("WORKER_POSITION_NOT_FOUND", "Worker Position not found with ID: " + workerPositionId);
    }
}
