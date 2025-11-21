package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AccountPurposeDTO;
import com.cdl.escrow.entity.AccountPurpose;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AccountPurposeMapper;
import com.cdl.escrow.repository.AccountPurposeRepository;
import com.cdl.escrow.service.AccountPurposeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountPurposeServiceImpl implements AccountPurposeService {

    private final AccountPurposeRepository repository;

    private final AccountPurposeMapper mapper;


    @Override
    public Page<AccountPurposeDTO> getAllAccountPurpose(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<AccountPurpose> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<AccountPurposeDTO> getAccountPurposeById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public AccountPurposeDTO saveAccountPurpose(AccountPurposeDTO accountPurposeDTO) {
        log.info("Saving new application language code");
        AccountPurpose entity = mapper.toEntity(accountPurposeDTO);
        AccountPurpose saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public AccountPurposeDTO updateAccountPurpose(Long id, AccountPurposeDTO accountPurposeDTO) {
        log.info("Updating application language code with ID: {}", id);

        AccountPurpose existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AccountPurpose toUpdate = mapper.toEntity(accountPurposeDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AccountPurpose updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteAccountPurposeById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteAccountPurposeById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }

}

