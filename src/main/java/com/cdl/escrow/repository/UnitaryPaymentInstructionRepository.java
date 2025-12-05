package com.cdl.escrow.repository;

import com.cdl.escrow.entity.UnitaryPaymentInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitaryPaymentInstructionRepository  extends JpaRepository<UnitaryPaymentInstruction, Long>, JpaSpecificationExecutor<UnitaryPaymentInstruction> {

    Optional<UnitaryPaymentInstruction> findByIdAndDeletedFalse(Long id);
}

