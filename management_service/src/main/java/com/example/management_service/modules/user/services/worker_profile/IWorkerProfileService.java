package com.example.management_service.modules.user.services.worker_profile;

import com.example.management_service.modules.user.entities.WorkerProfile;

import java.util.List;

public interface IWorkerProfileService {
    List<WorkerProfile> getAllWorkerProfiles();

    WorkerProfile getWorkerProfileById(Long workerProfileId);

    WorkerProfile createWorkerProfile(WorkerProfile workerProfile);

    WorkerProfile updateWorkerProfile(Long workerProfileId, WorkerProfile workerProfile);

    boolean deleteWorkerProfile(Long workerProfileId);
}
