package com.cdl.escrow.service;

import com.cdl.escrow.dto.AgreementTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgreementTypeService {

    Page<AgreementTypeDTO> getAllAgreementType(final Pageable pageable);

    Optional<AgreementTypeDTO> getAgreementTypeById(Long id);

    AgreementTypeDTO saveAgreementType(AgreementTypeDTO agreementTypeDTO);

    AgreementTypeDTO updateAgreementType(Long id, AgreementTypeDTO agreementTypeDTO);

    Boolean deleteAgreementTypeById(Long id);

    boolean softDeleteAgreementTypeById(Long id);
}
