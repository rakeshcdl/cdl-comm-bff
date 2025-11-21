package com.cdl.escrow.service;

import com.cdl.escrow.dto.BeneficiaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BeneficiaryService {

    Page<BeneficiaryDTO> getAllBeneficiary(final Pageable pageable);

    Optional<BeneficiaryDTO> getBeneficiaryById(Long id);

    BeneficiaryDTO saveBeneficiary(BeneficiaryDTO beneficiaryDTO);

    BeneficiaryDTO updateBeneficiary(Long id, BeneficiaryDTO beneficiaryDTO);

    Boolean deleteBeneficiaryById(Long id);

    boolean softDeleteBeneficiaryById(Long id);
}
