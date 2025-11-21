package com.cdl.escrow.repository;

import com.cdl.escrow.entity.ScheduledJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduledJobRepository extends JpaRepository<ScheduledJob, Long>, JpaSpecificationExecutor<ScheduledJob> {

    Optional<ScheduledJob> findByIdAndDeletedFalse(Long id);
}

