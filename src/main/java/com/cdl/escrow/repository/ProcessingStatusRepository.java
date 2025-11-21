package com.cdl.escrow.repository;

import com.cdl.escrow.entity.ProcessingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessingStatusRepository extends JpaRepository<ProcessingStatus, Long>, JpaSpecificationExecutor<ProcessingStatus> {

    Optional<ProcessingStatus> findByIdAndDeletedFalse(Long id);
}

