package com.cdl.escrow.service;

import com.cdl.escrow.dto.AgreementParametersDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgreementParametersService {

    Page<AgreementParametersDTO> getAllAgreementParameters(final Pageable pageable);

    Optional<AgreementParametersDTO> getAgreementParameterById(Long id);

    AgreementParametersDTO saveAgreementParameter(AgreementParametersDTO agreementParametersDTO);

    AgreementParametersDTO updateAgreementParameter(Long id, AgreementParametersDTO agreementParametersDTO);

    Boolean deleteAgreementParameterById(Long id);

    boolean softDeleteAgreementParameterById(Long id);
}
