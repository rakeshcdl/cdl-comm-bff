package com.cdl.escrow.service;

import com.cdl.escrow.dto.UnreconciledTransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UnreconciledTransactionService {

    Page<UnreconciledTransactionDTO> getAllUnreconciledTransaction(final Pageable pageable);

    Optional<UnreconciledTransactionDTO> getUnreconciledTransactionById(Long id);

    UnreconciledTransactionDTO saveUnreconciledTransaction(UnreconciledTransactionDTO unreconciledTransactionDTO);

    UnreconciledTransactionDTO updateUnreconciledTransaction(Long id, UnreconciledTransactionDTO unreconciledTransactionDTO);

    Boolean deleteUnreconciledTransactionById(Long id);

    boolean softDeleteUnreconciledTransactionById(Long id);
}
