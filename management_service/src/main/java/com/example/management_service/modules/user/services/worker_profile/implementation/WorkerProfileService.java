package com.example.management_service.modules.user.services.worker_profile.implementation;

import com.example.management_service.modules.user.entities.WorkerProfile;
import com.example.management_service.modules.user.exceptions.WorkerProfileNotFoundException;
import com.example.management_service.modules.user.mappers.WorkerProfileMapper;
import com.example.management_service.modules.user.repository.WorkerProfileRepository;
import com.example.management_service.modules.user.services.worker_profile.IWorkerProfileService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkerProfileService implements IWorkerProfileService {
    private final WorkerProfileRepository workerProfileRepository;
    private final WorkerProfileMapper workerProfileMapper;

    @Override
    public List<WorkerProfile> getAllWorkerProfiles() {
        return workerProfileRepository.findAll();
    }

    @Override
    public WorkerProfile getWorkerProfileById(Long workerProfileId) {
        return workerProfileRepository.findById(workerProfileId)
                .orElseThrow(() -> new WorkerProfileNotFoundException(workerProfileId));
    }

    @Override
    public WorkerProfile createWorkerProfile(WorkerProfile workerProfile) {
        return workerProfileRepository.save(workerProfile);
    }

    @Override
    public WorkerProfile updateWorkerProfile(Long workerProfileId, WorkerProfile workerProfile) {
        WorkerProfile workerProfileToUpdate = getWorkerProfileById(workerProfileId);

        return workerProfileRepository.save(workerProfileMapper.updateFrom(workerProfile, workerProfileToUpdate));
    }

    @Override
    public boolean deleteWorkerProfile(Long workerProfileId) {
        if (!workerProfileRepository.existsById(workerProfileId)) {
            throw new WorkerProfileNotFoundException(workerProfileId);
        }
        workerProfileRepository.deleteById(workerProfileId);
        return true;
    }
}
