package com.example.management_service.modules.user.services.worker_position;

import java.util.List;

import com.example.management_service.modules.user.entities.WorkerPosition;

public interface IWorkerPositionService {
    List<WorkerPosition> getAllWorkerPositions();

    WorkerPosition getWorkerPositionById(Long workerPositionId);

    WorkerPosition createWorkerPosition(WorkerPosition workerPosition);

    WorkerPosition updateWorkerPosition(Long workerPositionId, WorkerPosition workerPosition);

    boolean deleteWorkerPosition(Long workerPositionId);
}
