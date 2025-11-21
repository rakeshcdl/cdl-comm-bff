package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.UnreconciledTransactionDTO;
import com.cdl.escrow.service.UnreconciledTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnreconciledTransactionServiceImpl implements UnreconciledTransactionService {
    @Override
    public Page<UnreconciledTransactionDTO> getAllUnreconciledTransaction(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<UnreconciledTransactionDTO> getUnreconciledTransactionById(Long id) {
        return Optional.empty();
    }

    @Override
    public UnreconciledTransactionDTO saveUnreconciledTransaction(UnreconciledTransactionDTO unreconciledTransactionDTO) {
        return null;
    }

    @Override
    public UnreconciledTransactionDTO updateUnreconciledTransaction(Long id, UnreconciledTransactionDTO unreconciledTransactionDTO) {
        return null;
    }

    @Override
    public Boolean deleteUnreconciledTransactionById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteUnreconciledTransactionById(Long id) {
        return false;
    }
}
