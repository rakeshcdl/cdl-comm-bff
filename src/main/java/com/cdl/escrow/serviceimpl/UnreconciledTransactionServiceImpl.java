package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.UnreconciledTransactionDTO;
import com.cdl.escrow.entity.UnreconciledTransaction;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.UnreconciledTransactionMapper;
import com.cdl.escrow.repository.UnreconciledTransactionRepository;
import com.cdl.escrow.service.UnreconciledTransactionService;
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
public class UnreconciledTransactionServiceImpl implements UnreconciledTransactionService {

    private final UnreconciledTransactionRepository repository;

    private final UnreconciledTransactionMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<UnreconciledTransactionDTO> getAllUnreconciledTransaction(Pageable pageable) {
        log.debug("Fetching all unreconciled transaction, page: {}", pageable.getPageNumber());
        Page<UnreconciledTransaction> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UnreconciledTransactionDTO> getUnreconciledTransactionById(Long id) {
        log.debug("Fetching unreconciled transaction with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public UnreconciledTransactionDTO saveUnreconciledTransaction(UnreconciledTransactionDTO unreconciledTransactionDTO) {
        log.info("Saving new unreconciled transaction");
        UnreconciledTransaction entity = mapper.toEntity(unreconciledTransactionDTO);
        UnreconciledTransaction saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public UnreconciledTransactionDTO updateUnreconciledTransaction(Long id, UnreconciledTransactionDTO unreconciledTransactionDTO) {
        log.info("Updating unreconciled transaction with ID: {}", id);

        UnreconciledTransaction existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("unreconciled transaction not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        UnreconciledTransaction toUpdate = mapper.toEntity(unreconciledTransactionDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        UnreconciledTransaction updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteUnreconciledTransactionById(Long id) {
        log.info("Deleting unreconciled transaction with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("unreconciled transaction not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteUnreconciledTransactionById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
