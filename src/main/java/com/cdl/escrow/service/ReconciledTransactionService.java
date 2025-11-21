package com.cdl.escrow.service;

import com.cdl.escrow.dto.ReconciledTransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReconciledTransactionService {

    Page<ReconciledTransactionDTO> getAllReconciledTransaction(final Pageable pageable);

    Optional<ReconciledTransactionDTO> getReconciledTransactionById(Long id);

    ReconciledTransactionDTO saveReconciledTransaction(ReconciledTransactionDTO reconciledTransactionDTO);

    ReconciledTransactionDTO updateReconciledTransaction(Long id, ReconciledTransactionDTO reconciledTransactionDTO);

    Boolean deleteReconciledTransactionById(Long id);

    boolean softDeleteReconciledTransactionById(Long id);
}
