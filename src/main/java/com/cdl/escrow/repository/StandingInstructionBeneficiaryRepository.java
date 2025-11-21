package com.cdl.escrow.repository;

import com.cdl.escrow.entity.StandingInstructionBeneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StandingInstructionBeneficiaryRepository extends JpaRepository<StandingInstructionBeneficiary, Long>, JpaSpecificationExecutor<StandingInstructionBeneficiary> {

    Optional<StandingInstructionBeneficiary> findByIdAndDeletedFalse(Long id);
}

