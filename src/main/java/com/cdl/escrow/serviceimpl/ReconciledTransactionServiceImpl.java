package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ReconciledTransactionDTO;
import com.cdl.escrow.entity.ReconciledTransaction;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ReconciledTransactionMapper;
import com.cdl.escrow.repository.ReconciledTransactionRepository;
import com.cdl.escrow.service.ReconciledTransactionService;
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
public class ReconciledTransactionServiceImpl implements ReconciledTransactionService {
    private final ReconciledTransactionRepository repository;

    private final ReconciledTransactionMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ReconciledTransactionDTO> getAllReconciledTransaction(Pageable pageable) {
        log.debug("Fetching all reconciled transaction, page: {}", pageable.getPageNumber());
        Page<ReconciledTransaction> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReconciledTransactionDTO> getReconciledTransactionById(Long id) {
        log.debug("Fetching reconciled transaction with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public ReconciledTransactionDTO saveReconciledTransaction(ReconciledTransactionDTO reconciledTransactionDTO) {
        log.info("Saving new reconciled transaction");
        ReconciledTransaction entity = mapper.toEntity(reconciledTransactionDTO);
        ReconciledTransaction saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public ReconciledTransactionDTO updateReconciledTransaction(Long id, ReconciledTransactionDTO reconciledTransactionDTO) {
        log.info("Updating reconciled transaction with ID: {}", id);

        ReconciledTransaction existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("reconciled transaction not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ReconciledTransaction toUpdate = mapper.toEntity(reconciledTransactionDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        ReconciledTransaction updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteReconciledTransactionById(Long id) {
        log.info("Deleting reconciled transaction with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("reconciled transaction not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteReconciledTransactionById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
