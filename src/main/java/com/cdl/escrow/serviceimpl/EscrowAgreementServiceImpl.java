package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.EscrowAgreementDTO;
import com.cdl.escrow.service.EscrowAgreementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EscrowAgreementServiceImpl implements EscrowAgreementService {
    @Override
    public Page<EscrowAgreementDTO> getAllEscrowAgreement(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<EscrowAgreementDTO> getEscrowAgreementById(Long id) {
        return Optional.empty();
    }

    @Override
    public EscrowAgreementDTO saveEscrowAgreement(EscrowAgreementDTO escrowAgreementDTO) {
        return null;
    }

    @Override
    public EscrowAgreementDTO updateEscrowAgreement(Long id, EscrowAgreementDTO escrowAgreementDTO) {
        return null;
    }

    @Override
    public Boolean deleteEscrowAgreementById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteEscrowAgreementById(Long id) {
        return false;
    }
}
