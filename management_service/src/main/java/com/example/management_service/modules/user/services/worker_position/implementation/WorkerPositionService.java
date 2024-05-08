package com.example.management_service.modules.user.services.worker_position.implementation;

import com.example.management_service.modules.user.entities.WorkerPosition;
import com.example.management_service.modules.user.exceptions.WorkerPositionNotFoundException;
import com.example.management_service.modules.user.mappers.WorkerPositionMapper;
import com.example.management_service.modules.user.repository.WorkerPositionRepository;
import com.example.management_service.modules.user.services.worker_position.IWorkerPositionService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerPositionService implements IWorkerPositionService {
    private WorkerPositionRepository workerPositionRepository;
    private WorkerPositionMapper workerPositionMapper;

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

        WorkerPosition workerPositionToUpdate = getWorkerPositionById(workerPositionId);

        return workerPositionRepository
                .save(workerPositionMapper.updateFrom(workerPosition, workerPositionToUpdate));
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
