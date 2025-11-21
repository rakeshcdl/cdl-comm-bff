package com.cdl.escrow.service;

import com.cdl.escrow.dto.AgreementBudgetPlanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgreementBudgetPlanService {

    Page<AgreementBudgetPlanDTO> getAllAgreementBudgetPlan(final Pageable pageable);

    Optional<AgreementBudgetPlanDTO> getAgreementBudgetPlanById(Long id);

    AgreementBudgetPlanDTO saveAgreementBudgetPlan(AgreementBudgetPlanDTO agreementBudgetPlanDTO);

    AgreementBudgetPlanDTO updateAgreementBudgetPlan(Long id, AgreementBudgetPlanDTO agreementBudgetPlanDTO);

    Boolean deleteAgreementBudgetPlanById(Long id);

    boolean softDeleteAgreementBudgetPlanById(Long id);
}
