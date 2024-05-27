package com.example.management_service.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.management_service.modules.user.entities.WorkerPosition;

@Repository
public interface WorkerPositionRepository extends JpaRepository<WorkerPosition, Long> {

}
