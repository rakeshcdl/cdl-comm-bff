package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.UnitaryPaymentInstructionDTO;
import com.cdl.escrow.entity.UnitaryPaymentInstruction;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.UnitaryPaymentInstructionMapper;
import com.cdl.escrow.repository.UnitaryPaymentInstructionRepository;
import com.cdl.escrow.service.UnitaryPaymentInstructionService;
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
public class UnitaryPaymentInstructionServiceImpl implements UnitaryPaymentInstructionService {


    private final UnitaryPaymentInstructionRepository repository;

    private final UnitaryPaymentInstructionMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<UnitaryPaymentInstructionDTO> getAllUnitaryPaymentInstruction(Pageable pageable) {
        log.debug("Fetching all Unitary Payment Instruction, page: {}", pageable.getPageNumber());
        Page<UnitaryPaymentInstruction> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UnitaryPaymentInstructionDTO> getUnitaryPaymentInstructionById(Long id) {
        log.debug("Fetching Unitary Payment Instruction with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public UnitaryPaymentInstructionDTO saveUnitaryPaymentInstruction(UnitaryPaymentInstructionDTO unitaryPaymentInstructionDTO) {
        log.info("Saving new Unitary Payment Instruction");
        UnitaryPaymentInstruction entity = mapper.toEntity(unitaryPaymentInstructionDTO);
        UnitaryPaymentInstruction saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public UnitaryPaymentInstructionDTO updateUnitaryPaymentInstruction(Long id, UnitaryPaymentInstructionDTO unitaryPaymentInstructionDTO) {
        log.info("Updating Unitary Payment Instruction with ID: {}", id);

        UnitaryPaymentInstruction existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Unitary Payment Instruction not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        UnitaryPaymentInstruction toUpdate = mapper.toEntity(unitaryPaymentInstructionDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        UnitaryPaymentInstruction updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteUnitaryPaymentInstructionById(Long id) {
        log.info("Deleting Unitary Payment Instruction with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Unitary Payment Instruction not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteUnitaryPaymentInstructionById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
