package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.GeneralLedgerAccountDTO;
import com.cdl.escrow.entity.GeneralLedgerAccount;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.GeneralLedgerAccountMapper;
import com.cdl.escrow.repository.GeneralLedgerAccountRepository;
import com.cdl.escrow.service.GeneralLedgerAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeneralLedgerAccountServiceImpl implements GeneralLedgerAccountService {
    private final GeneralLedgerAccountRepository repository;

    private final GeneralLedgerAccountMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<GeneralLedgerAccountDTO> getAllGeneralLedgerAccount(Pageable pageable) {
        log.debug("Fetching all general ledger account, page: {}", pageable.getPageNumber());
        Page<GeneralLedgerAccount> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GeneralLedgerAccountDTO> getGeneralLedgerAccountById(Long id) {
        log.debug("Fetching general ledger account with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public GeneralLedgerAccountDTO saveGeneralLedgerAccount(GeneralLedgerAccountDTO generalLedgerAccountDTO) {
        log.info("Saving new general ledger account");
        GeneralLedgerAccount entity = mapper.toEntity(generalLedgerAccountDTO);
        GeneralLedgerAccount saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public GeneralLedgerAccountDTO updateGeneralLedgerAccount(Long id, GeneralLedgerAccountDTO generalLedgerAccountDTO) {
        log.info("Updating general ledger account with ID: {}", id);

        GeneralLedgerAccount existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("general ledger account not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        GeneralLedgerAccount toUpdate = mapper.toEntity(generalLedgerAccountDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        GeneralLedgerAccount updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteGeneralLedgerAccountById(Long id) {
        log.info("Deleting general ledger account with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("general ledger account not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteGeneralLedgerAccountById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
