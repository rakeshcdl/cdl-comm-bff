package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.StandingInstructionBeneficiaryDTO;
import com.cdl.escrow.entity.StandingInstructionBeneficiary;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.StandingInstructionBeneficiaryMapper;
import com.cdl.escrow.repository.StandingInstructionBeneficiaryRepository;
import com.cdl.escrow.service.StandingInstructionBeneficiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StandingInstructionBeneficiaryServiceImpl implements StandingInstructionBeneficiaryService {

    private final StandingInstructionBeneficiaryRepository repository;

    private final StandingInstructionBeneficiaryMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<StandingInstructionBeneficiaryDTO> getAllStandingInstructionBeneficiary(Pageable pageable) {
        log.debug("Fetching all standing instruction beneficiary, page: {}", pageable.getPageNumber());
        Page<StandingInstructionBeneficiary> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StandingInstructionBeneficiaryDTO> getStandingInstructionBeneficiaryById(Long id) {
        log.debug("Fetching standing instruction beneficiary with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public StandingInstructionBeneficiaryDTO saveStandingInstructionBeneficiary(StandingInstructionBeneficiaryDTO standingInstructionBeneficiaryDTO) {
        log.info("Saving new standing instruction beneficiary");
        StandingInstructionBeneficiary entity = mapper.toEntity(standingInstructionBeneficiaryDTO);
        StandingInstructionBeneficiary saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public StandingInstructionBeneficiaryDTO updateStandingInstructionBeneficiary(Long id, StandingInstructionBeneficiaryDTO standingInstructionBeneficiaryDTO) {
        log.info("Updating standing instruction beneficiary with ID: {}", id);

        StandingInstructionBeneficiary existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("standing instruction beneficiary not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        StandingInstructionBeneficiary toUpdate = mapper.toEntity(standingInstructionBeneficiaryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        StandingInstructionBeneficiary updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteStandingInstructionBeneficiaryById(Long id) {
        log.info("Deleting standing instruction beneficiary with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("standing instruction beneficiary not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteStandingInstructionBeneficiaryById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
