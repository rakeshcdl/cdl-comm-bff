package com.cdl.escrow.service;

import com.cdl.escrow.dto.AgreementSegmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgreementSegmentService {

    Page<AgreementSegmentDTO> getAllAgreementSegment(final Pageable pageable);

    Optional<AgreementSegmentDTO> getAgreementSegmentById(Long id);

    AgreementSegmentDTO saveAgreementSegment(AgreementSegmentDTO agreementSegmentDTO);

    AgreementSegmentDTO updateAgreementSegment(Long id, AgreementSegmentDTO agreementSegmentDTO);

    Boolean deleteAgreementSegmentById(Long id);

    boolean softDeleteAgreementSegmentById(Long id);
}
