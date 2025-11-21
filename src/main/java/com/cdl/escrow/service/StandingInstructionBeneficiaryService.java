package com.cdl.escrow.service;

import com.cdl.escrow.dto.StandingInstructionBeneficiaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StandingInstructionBeneficiaryService {

    Page<StandingInstructionBeneficiaryDTO> getAllStandingInstructionBeneficiary(final Pageable pageable);

    Optional<StandingInstructionBeneficiaryDTO> getStandingInstructionBeneficiaryById(Long id);

    StandingInstructionBeneficiaryDTO saveStandingInstructionBeneficiary(StandingInstructionBeneficiaryDTO standingInstructionBeneficiaryDTO);

    StandingInstructionBeneficiaryDTO updateStandingInstructionBeneficiary(Long id, StandingInstructionBeneficiaryDTO standingInstructionBeneficiaryDTO);

    Boolean deleteStandingInstructionBeneficiaryById(Long id);

    boolean softDeleteStandingInstructionBeneficiaryById(Long id);
}
