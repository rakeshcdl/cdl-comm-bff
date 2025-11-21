package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.TransactionProcessingHistoryDTO;
import com.cdl.escrow.repository.TransactionProcessingHistoryRepository;
import com.cdl.escrow.service.TransactionProcessingHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionProcessingHistoryServiceImpl implements TransactionProcessingHistoryService {
    @Override
    public Page<TransactionProcessingHistoryDTO> getAllTransactionProcessingHistory(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<TransactionProcessingHistoryDTO> getTransactionProcessingHistoryById(Long id) {
        return Optional.empty();
    }

    @Override
    public TransactionProcessingHistoryDTO saveTransactionProcessingHistory(TransactionProcessingHistoryDTO transactionProcessingHistoryDTO) {
        return null;
    }

    @Override
    public TransactionProcessingHistoryDTO updateTransactionProcessingHistory(Long id, TransactionProcessingHistoryDTO transactionProcessingHistoryDTO) {
        return null;
    }

    @Override
    public Boolean deleteTransactionProcessingHistoryById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteTransactionProcessingHistoryById(Long id) {
        return false;
    }
}
