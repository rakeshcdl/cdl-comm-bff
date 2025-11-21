package com.cdl.escrow.service;

import com.cdl.escrow.dto.TransactionProcessingHistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TransactionProcessingHistoryService {
    Page<TransactionProcessingHistoryDTO> getAllTransactionProcessingHistory(final Pageable pageable);

    Optional<TransactionProcessingHistoryDTO> getTransactionProcessingHistoryById(Long id);

    TransactionProcessingHistoryDTO saveTransactionProcessingHistory(TransactionProcessingHistoryDTO transactionProcessingHistoryDTO);

    TransactionProcessingHistoryDTO updateTransactionProcessingHistory(Long id, TransactionProcessingHistoryDTO transactionProcessingHistoryDTO);

    Boolean deleteTransactionProcessingHistoryById(Long id);

    boolean softDeleteTransactionProcessingHistoryById(Long id);
}
