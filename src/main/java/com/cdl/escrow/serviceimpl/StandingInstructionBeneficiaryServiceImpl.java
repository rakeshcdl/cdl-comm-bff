package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.StandingInstructionBeneficiaryDTO;
import com.cdl.escrow.dto.StandingInstructionDTO;
import com.cdl.escrow.service.StandingInstructionBeneficiaryService;
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
public class StandingInstructionBeneficiaryServiceImpl implements StandingInstructionBeneficiaryService {

    @Override
    public Page<StandingInstructionBeneficiaryDTO> getAllStandingInstructionBeneficiary(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<StandingInstructionBeneficiaryDTO> getStandingInstructionBeneficiaryById(Long id) {
        return Optional.empty();
    }

    @Override
    public StandingInstructionBeneficiaryDTO saveStandingInstructionBeneficiary(StandingInstructionBeneficiaryDTO standingInstructionBeneficiaryDTO) {
        return null;
    }

    @Override
    public StandingInstructionBeneficiaryDTO updateStandingInstructionBeneficiary(Long id, StandingInstructionBeneficiaryDTO standingInstructionBeneficiaryDTO) {
        return null;
    }

    @Override
    public Boolean deleteStandingInstructionBeneficiaryById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteStandingInstructionBeneficiaryById(Long id) {
        return false;
    }
}
