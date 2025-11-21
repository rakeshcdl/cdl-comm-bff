package com.cdl.escrow.service;

import com.cdl.escrow.dto.GeneralLedgerAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GeneralLedgerAccountService {

    Page<GeneralLedgerAccountDTO> getAllGeneralLedgerAccount(final Pageable pageable);

    Optional<GeneralLedgerAccountDTO> getGeneralLedgerAccountById(Long id);

    GeneralLedgerAccountDTO saveGeneralLedgerAccount(GeneralLedgerAccountDTO generalLedgerAccountDTO);

    GeneralLedgerAccountDTO updateGeneralLedgerAccount(Long id, GeneralLedgerAccountDTO generalLedgerAccountDTO);

    Boolean deleteGeneralLedgerAccountById(Long id);

    boolean softDeleteGeneralLedgerAccountById(Long id);
}
