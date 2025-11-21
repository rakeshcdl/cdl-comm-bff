package com.cdl.escrow.service;

import com.cdl.escrow.dto.StandingInstructionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StandingInstructionService {

    Page<StandingInstructionDTO> getAllStandingInstruction(final Pageable pageable);

    Optional<StandingInstructionDTO> getStandingInstructionById(Long id);

    StandingInstructionDTO saveStandingInstruction(StandingInstructionDTO standingInstructionDTO);

    StandingInstructionDTO updateStandingInstruction(Long id, StandingInstructionDTO standingInstructionDTO);

    Boolean deleteStandingInstructionById(Long id);

    boolean softDeleteStandingInstructionById(Long id);
}
