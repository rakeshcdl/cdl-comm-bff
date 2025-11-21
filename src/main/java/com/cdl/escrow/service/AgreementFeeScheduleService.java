package com.cdl.escrow.service;

import com.cdl.escrow.dto.AgreementFeeScheduleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgreementFeeScheduleService {

    Page<AgreementFeeScheduleDTO> getAllAgreementFeeSchedule(final Pageable pageable);

    Optional<AgreementFeeScheduleDTO> getAgreementFeeScheduleById(Long id);

    AgreementFeeScheduleDTO saveAgreementFeeSchedule(AgreementFeeScheduleDTO agreementFeeScheduleDTO);

    AgreementFeeScheduleDTO updateAgreementFeeSchedule(Long id, AgreementFeeScheduleDTO agreementFeeScheduleDTO);

    Boolean deleteAgreementFeeScheduleById(Long id);

    boolean softDeleteAgreementFeeScheduleById(Long id);
}
