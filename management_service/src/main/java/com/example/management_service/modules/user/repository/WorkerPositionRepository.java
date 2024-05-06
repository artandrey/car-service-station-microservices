package com.example.management_service.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.management_service.modules.user.entities.WorkerPosition;

public interface WorkerPositionRepository extends JpaRepository<WorkerPosition, Long> {

}
