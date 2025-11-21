package com.cdl.escrow.repository;

import com.cdl.escrow.entity.UnreconciledTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnreconciledTransactionRepository  extends JpaRepository<UnreconciledTransaction, Long>, JpaSpecificationExecutor<UnreconciledTransaction> {

    Optional<UnreconciledTransaction> findByIdAndDeletedFalse(Long id);
}