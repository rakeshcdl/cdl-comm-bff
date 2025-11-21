package com.cdl.escrow.service;

import com.cdl.escrow.dto.EscrowAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EscrowAccountService {

    Page<EscrowAccountDTO> getAllEscrowAccount(final Pageable pageable);

    Optional<EscrowAccountDTO> getEscrowAccountById(Long id);

    EscrowAccountDTO saveEscrowAccount(EscrowAccountDTO escrowAccountDTO);

    EscrowAccountDTO updateEscrowAccount(Long id, EscrowAccountDTO escrowAccountDTO);

    Boolean deleteEscrowAccountById(Long id);

    boolean softDeleteEscrowAccountById(Long id);
}
