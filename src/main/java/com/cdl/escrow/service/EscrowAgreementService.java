package com.cdl.escrow.service;

import com.cdl.escrow.dto.EscrowAgreementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EscrowAgreementService {

    Page<EscrowAgreementDTO> getAllEscrowAgreement(final Pageable pageable);

    Optional<EscrowAgreementDTO> getEscrowAgreementById(Long id);

    EscrowAgreementDTO saveEscrowAgreement(EscrowAgreementDTO escrowAgreementDTO);

    EscrowAgreementDTO updateEscrowAgreement(Long id, EscrowAgreementDTO escrowAgreementDTO);

    Boolean deleteEscrowAgreementById(Long id);

    boolean softDeleteEscrowAgreementById(Long id);
}
