package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.StandingInstructionDTO;
import com.cdl.escrow.service.StandingInstructionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StandingInstructionServiceImpl implements StandingInstructionService {
    @Override
    public Page<StandingInstructionDTO> getAllStandingInstruction(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<StandingInstructionDTO> getStandingInstructionById(Long id) {
        return Optional.empty();
    }

    @Override
    public StandingInstructionDTO saveStandingInstruction(StandingInstructionDTO standingInstructionDTO) {
        return null;
    }

    @Override
    public StandingInstructionDTO updateStandingInstruction(Long id, StandingInstructionDTO standingInstructionDTO) {
        return null;
    }

    @Override
    public Boolean deleteStandingInstructionById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteStandingInstructionById(Long id) {
        return false;
    }
}
