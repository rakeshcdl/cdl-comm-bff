package com.cdl.escrow.repository;

import com.cdl.escrow.entity.StandingInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StandingInstructionRepository extends JpaRepository<StandingInstruction, Long>, JpaSpecificationExecutor<StandingInstruction> {

    Optional<StandingInstruction> findByIdAndDeletedFalse(Long id);
}