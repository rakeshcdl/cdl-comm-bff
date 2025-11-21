package com.cdl.escrow.service;

import com.cdl.escrow.dto.PaymentInstructionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PaymentInstructionService {

    Page<PaymentInstructionDTO> getAllPaymentInstruction(final Pageable pageable);

    Optional<PaymentInstructionDTO> getPaymentInstructionById(Long id);

    PaymentInstructionDTO savePaymentInstruction(PaymentInstructionDTO paymentInstructionDTO);

    PaymentInstructionDTO updatePaymentInstruction(Long id, PaymentInstructionDTO paymentInstructionDTO);

    Boolean deletePaymentInstructionById(Long id);

    boolean softDeletePaymentInstructionById(Long id);
}
