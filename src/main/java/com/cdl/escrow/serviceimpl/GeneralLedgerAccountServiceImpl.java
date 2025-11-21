package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.GeneralLedgerAccountDTO;
import com.cdl.escrow.service.GeneralLedgerAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeneralLedgerAccountServiceImpl implements GeneralLedgerAccountService {
    @Override
    public Page<GeneralLedgerAccountDTO> getAllGeneralLedgerAccount(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<GeneralLedgerAccountDTO> getGeneralLedgerAccountById(Long id) {
        return Optional.empty();
    }

    @Override
    public GeneralLedgerAccountDTO saveGeneralLedgerAccount(GeneralLedgerAccountDTO generalLedgerAccountDTO) {
        return null;
    }

    @Override
    public GeneralLedgerAccountDTO updateGeneralLedgerAccount(Long id, GeneralLedgerAccountDTO generalLedgerAccountDTO) {
        return null;
    }

    @Override
    public Boolean deleteGeneralLedgerAccountById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteGeneralLedgerAccountById(Long id) {
        return false;
    }
}
