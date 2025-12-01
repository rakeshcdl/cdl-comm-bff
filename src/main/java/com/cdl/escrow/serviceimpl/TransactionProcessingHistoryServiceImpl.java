package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.TransactionProcessingHistoryDTO;
import com.cdl.escrow.entity.TransactionProcessingHistory;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.TransactionProcessingHistoryMapper;
import com.cdl.escrow.repository.TransactionProcessingHistoryRepository;
import com.cdl.escrow.service.TransactionProcessingHistoryService;
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
public class TransactionProcessingHistoryServiceImpl implements TransactionProcessingHistoryService {

    private final TransactionProcessingHistoryRepository repository;

    private final TransactionProcessingHistoryMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<TransactionProcessingHistoryDTO> getAllTransactionProcessingHistory(Pageable pageable) {
        log.debug("Fetching all transaction processing history, page: {}", pageable.getPageNumber());
        Page<TransactionProcessingHistory> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionProcessingHistoryDTO> getTransactionProcessingHistoryById(Long id) {
        log.debug("Fetching transaction processing history with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public TransactionProcessingHistoryDTO saveTransactionProcessingHistory(TransactionProcessingHistoryDTO transactionProcessingHistoryDTO) {
        log.info("Saving new transaction processing history");
        TransactionProcessingHistory entity = mapper.toEntity(transactionProcessingHistoryDTO);
        TransactionProcessingHistory saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public TransactionProcessingHistoryDTO updateTransactionProcessingHistory(Long id, TransactionProcessingHistoryDTO transactionProcessingHistoryDTO) {
        log.info("Updating transaction processing history with ID: {}", id);

        TransactionProcessingHistory existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("transaction processing history not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        TransactionProcessingHistory toUpdate = mapper.toEntity(transactionProcessingHistoryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        TransactionProcessingHistory updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteTransactionProcessingHistoryById(Long id) {
        log.info("Deleting transaction processing history with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("transaction processing history not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteTransactionProcessingHistoryById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
