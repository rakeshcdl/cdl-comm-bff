package com.cdl.escrow.service;

import com.cdl.escrow.dto.UnitaryPaymentInstructionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UnitaryPaymentInstructionService {

    Page<UnitaryPaymentInstructionDTO> getAllUnitaryPaymentInstruction(final Pageable pageable);

    Optional<UnitaryPaymentInstructionDTO> getUnitaryPaymentInstructionById(Long id);

    UnitaryPaymentInstructionDTO saveUnitaryPaymentInstruction(UnitaryPaymentInstructionDTO unitaryPaymentInstructionDTO);

    UnitaryPaymentInstructionDTO updateUnitaryPaymentInstruction(Long id, UnitaryPaymentInstructionDTO unitaryPaymentInstructionDTO);

    Boolean deleteUnitaryPaymentInstructionById(Long id);

    boolean softDeleteUnitaryPaymentInstructionById(Long id);
}
