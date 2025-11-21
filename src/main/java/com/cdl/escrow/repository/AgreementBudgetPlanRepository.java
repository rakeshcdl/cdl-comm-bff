package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AgreementBudgetPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementBudgetPlanRepository extends JpaRepository<AgreementBudgetPlan, Long>, JpaSpecificationExecutor<AgreementBudgetPlan> {

    Optional<AgreementBudgetPlan> findByIdAndDeletedFalse(Long id);
}

