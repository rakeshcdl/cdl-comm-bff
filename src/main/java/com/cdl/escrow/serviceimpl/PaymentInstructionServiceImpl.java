package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.PaymentInstructionDTO;
import com.cdl.escrow.service.PaymentInstructionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentInstructionServiceImpl implements PaymentInstructionService {
    @Override
    public Page<PaymentInstructionDTO> getAllPaymentInstruction(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PaymentInstructionDTO> getPaymentInstructionById(Long id) {
        return Optional.empty();
    }

    @Override
    public PaymentInstructionDTO savePaymentInstruction(PaymentInstructionDTO paymentInstructionDTO) {
        return null;
    }

    @Override
    public PaymentInstructionDTO updatePaymentInstruction(Long id, PaymentInstructionDTO paymentInstructionDTO) {
        return null;
    }

    @Override
    public Boolean deletePaymentInstructionById(Long id) {
        return null;
    }

    @Override
    public boolean softDeletePaymentInstructionById(Long id) {
        return false;
    }
}
