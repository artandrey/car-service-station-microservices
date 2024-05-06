package com.example.management_service.modules.user.exceptions;

import com.example.management_service.shared.exceptions.ClientException;

public class WorkerProfileNotFoundException extends ClientException {
    public WorkerProfileNotFoundException(Long workerProfileId) {
        super("WORKER_PROFILE_NOT_FOUND", "Worker Profile not found with ID: " + workerProfileId);
    }
}
