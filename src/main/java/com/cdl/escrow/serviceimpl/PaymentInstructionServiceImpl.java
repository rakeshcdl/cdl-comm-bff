package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.PaymentInstructionDTO;
import com.cdl.escrow.entity.PaymentInstruction;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.PaymentInstructionMapper;
import com.cdl.escrow.repository.PaymentInstructionRepository;
import com.cdl.escrow.service.PaymentInstructionService;
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
public class PaymentInstructionServiceImpl implements PaymentInstructionService {

    private final PaymentInstructionRepository repository;

    private final PaymentInstructionMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<PaymentInstructionDTO> getAllPaymentInstruction(Pageable pageable) {
        log.debug("Fetching all party instruction, page: {}", pageable.getPageNumber());
        Page<PaymentInstruction> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentInstructionDTO> getPaymentInstructionById(Long id) {
        log.debug("Fetching party instruction with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public PaymentInstructionDTO savePaymentInstruction(PaymentInstructionDTO paymentInstructionDTO) {
        log.info("Saving new party instruction");
        PaymentInstruction entity = mapper.toEntity(paymentInstructionDTO);
        PaymentInstruction saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public PaymentInstructionDTO updatePaymentInstruction(Long id, PaymentInstructionDTO paymentInstructionDTO) {
        log.info("Updating party instruction with ID: {}", id);

        PaymentInstruction existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("party instruction not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        PaymentInstruction toUpdate = mapper.toEntity(paymentInstructionDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        PaymentInstruction updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deletePaymentInstructionById(Long id) {
        log.info("Deleting party instruction with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("party instruction not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeletePaymentInstructionById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
