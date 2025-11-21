package com.cdl.escrow.repository;

import com.cdl.escrow.entity.TransactionProcessingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionProcessingHistoryRepository  extends JpaRepository<TransactionProcessingHistory, Long>, JpaSpecificationExecutor<TransactionProcessingHistory> {

    Optional<TransactionProcessingHistory> findByIdAndDeletedFalse(Long id);
}
