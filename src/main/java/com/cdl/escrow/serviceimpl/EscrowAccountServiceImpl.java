package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.EscrowAccountDTO;
import com.cdl.escrow.entity.EscrowAccount;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.EscrowAccountMapper;
import com.cdl.escrow.repository.EscrowAccountRepository;
import com.cdl.escrow.service.EscrowAccountService;
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
public class EscrowAccountServiceImpl implements EscrowAccountService {

    private final EscrowAccountRepository repository;

    private final EscrowAccountMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<EscrowAccountDTO> getAllEscrowAccount(Pageable pageable) {
        log.debug("Fetching all escrow account, page: {}", pageable.getPageNumber());
        Page<EscrowAccount> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EscrowAccountDTO> getEscrowAccountById(Long id) {
        log.debug("Fetching escrow account with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public EscrowAccountDTO saveEscrowAccount(EscrowAccountDTO escrowAccountDTO) {
        log.info("Saving new escrow account");
        EscrowAccount entity = mapper.toEntity(escrowAccountDTO);
        EscrowAccount saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public EscrowAccountDTO updateEscrowAccount(Long id, EscrowAccountDTO escrowAccountDTO) {
        log.info("Updating escrow account with ID: {}", id);

        EscrowAccount existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("escrow account not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        EscrowAccount toUpdate = mapper.toEntity(escrowAccountDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        EscrowAccount updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteEscrowAccountById(Long id) {
        log.info("Deleting escrow account with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("escrow account not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteEscrowAccountById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
