package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.EscrowAccountDTO;
import com.cdl.escrow.service.EscrowAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EscrowAccountServiceImpl implements EscrowAccountService {
    @Override
    public Page<EscrowAccountDTO> getAllEscrowAccount(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<EscrowAccountDTO> getEscrowAccountById(Long id) {
        return Optional.empty();
    }

    @Override
    public EscrowAccountDTO saveEscrowAccount(EscrowAccountDTO escrowAccountDTO) {
        return null;
    }

    @Override
    public EscrowAccountDTO updateEscrowAccount(Long id, EscrowAccountDTO escrowAccountDTO) {
        return null;
    }

    @Override
    public Boolean deleteEscrowAccountById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteEscrowAccountById(Long id) {
        return false;
    }
}
