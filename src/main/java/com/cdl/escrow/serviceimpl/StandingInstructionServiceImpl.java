package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.StandingInstructionDTO;
import com.cdl.escrow.entity.StandingInstruction;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.StandingInstructionMapper;
import com.cdl.escrow.repository.StandingInstructionRepository;
import com.cdl.escrow.service.StandingInstructionService;
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
public class StandingInstructionServiceImpl implements StandingInstructionService {


    private final StandingInstructionRepository repository;

    private final StandingInstructionMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<StandingInstructionDTO> getAllStandingInstruction(Pageable pageable) {
        log.debug("Fetching all standing instruction, page: {}", pageable.getPageNumber());
        Page<StandingInstruction> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StandingInstructionDTO> getStandingInstructionById(Long id) {
        log.debug("Fetching standing instruction with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public StandingInstructionDTO saveStandingInstruction(StandingInstructionDTO standingInstructionDTO) {
        log.info("Saving new standing instruction");
        StandingInstruction entity = mapper.toEntity(standingInstructionDTO);
        StandingInstruction saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public StandingInstructionDTO updateStandingInstruction(Long id, StandingInstructionDTO standingInstructionDTO) {
        log.info("Updating standing instruction with ID: {}", id);

        StandingInstruction existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("standing instruction not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        StandingInstruction toUpdate = mapper.toEntity(standingInstructionDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        StandingInstruction updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteStandingInstructionById(Long id) {
        log.info("Deleting standing instruction with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("standing instruction not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteStandingInstructionById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
