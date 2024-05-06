package com.example.management_service.modules.user.services.worker_position.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.management_service.modules.user.entities.WorkerPosition;
import com.example.management_service.modules.user.exceptions.WorkerPositionNotFoundException;
import com.example.management_service.modules.user.repository.WorkerPositionRepository;
import com.example.management_service.modules.user.services.worker_position.IWorkerPositionService;

@Service
public class WorkerPositionService implements IWorkerPositionService {
    private final WorkerPositionRepository workerPositionRepository;

    public WorkerPositionService(WorkerPositionRepository workerPositionRepository) {
        this.workerPositionRepository = workerPositionRepository;
    }

    @Override
    public List<WorkerPosition> getAllWorkerPositions() {
        return workerPositionRepository.findAll();
    }

    @Override
    public WorkerPosition getWorkerPositionById(Long workerPositionId) {
        return workerPositionRepository.findById(workerPositionId)
                .orElseThrow(() -> new WorkerPositionNotFoundException(workerPositionId));
    }

    @Override
    public WorkerPosition createWorkerPosition(WorkerPosition workerPosition) {
        return workerPositionRepository.save(workerPosition);
    }

    @Override
    public WorkerPosition updateWorkerPosition(Long workerPositionId, WorkerPosition workerPosition) {
        if (!workerPositionRepository.existsById(workerPositionId)) {
            throw new WorkerPositionNotFoundException(workerPositionId);
        }
        workerPosition.setId(workerPositionId);
        return workerPositionRepository.save(workerPosition);
    }

    @Override
    public boolean deleteWorkerPosition(Long workerPositionId) {
        if (!workerPositionRepository.existsById(workerPositionId)) {
            throw new WorkerPositionNotFoundException(workerPositionId);
        }
        workerPositionRepository.deleteById(workerPositionId);
        return true;
    }
}
