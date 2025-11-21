package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ReconciledTransactionDTO;
import com.cdl.escrow.service.ReconciledTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReconciledTransactionServiceImpl implements ReconciledTransactionService {
    @Override
    public Page<ReconciledTransactionDTO> getAllReconciledTransaction(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ReconciledTransactionDTO> getReconciledTransactionById(Long id) {
        return Optional.empty();
    }

    @Override
    public ReconciledTransactionDTO saveReconciledTransaction(ReconciledTransactionDTO reconciledTransactionDTO) {
        return null;
    }

    @Override
    public ReconciledTransactionDTO updateReconciledTransaction(Long id, ReconciledTransactionDTO reconciledTransactionDTO) {
        return null;
    }

    @Override
    public Boolean deleteReconciledTransactionById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteReconciledTransactionById(Long id) {
        return false;
    }
}
