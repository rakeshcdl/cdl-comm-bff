package com.cdl.escrow.service;

import com.cdl.escrow.dto.AgreementSignatoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgreementSignatoryService {

    Page<AgreementSignatoryDTO> getAllAgreementSignatory(final Pageable pageable);

    Optional<AgreementSignatoryDTO> getAgreementSignatoryById(Long id);

    AgreementSignatoryDTO saveAgreementSignatory(AgreementSignatoryDTO agreementSignatoryDTO);

    AgreementSignatoryDTO updateAgreementSignatory(Long id, AgreementSignatoryDTO agreementSignatoryDTO);

    Boolean deleteAgreementSignatoryById(Long id);

    boolean softDeleteAgreementSignatoryById(Long id);
}
