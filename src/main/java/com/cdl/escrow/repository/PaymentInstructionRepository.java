package com.cdl.escrow.repository;

import com.cdl.escrow.entity.PaymentInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentInstructionRepository extends JpaRepository<PaymentInstruction, Long>, JpaSpecificationExecutor<PaymentInstruction> {

    Optional<PaymentInstruction> findByIdAndDeletedFalse(Long id);
}

