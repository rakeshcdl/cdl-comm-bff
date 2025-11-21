package com.cdl.escrow.service;

import com.cdl.escrow.dto.AgreementSubTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgreementSubTypeService {

    Page<AgreementSubTypeDTO> getAllAgreementSubType(final Pageable pageable);

    Optional<AgreementSubTypeDTO> getAgreementSubTypeById(Long id);

    AgreementSubTypeDTO saveAgreementSubType(AgreementSubTypeDTO agreementSubTypeDTO);

    AgreementSubTypeDTO updateAgreementSubType(Long id, AgreementSubTypeDTO agreementSubTypeDTO);

    Boolean deleteAgreementSubTypeById(Long id);

    boolean softDeleteAgreementSubTypeById(Long id);
}
