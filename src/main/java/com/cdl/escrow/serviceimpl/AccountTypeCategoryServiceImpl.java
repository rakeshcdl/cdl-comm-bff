package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AccountTypeCategoryDTO;
import com.cdl.escrow.entity.AccountTypeCategory;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AccountTypeCategoryMapper;
import com.cdl.escrow.repository.AccountTypeCategoryRepository;
import com.cdl.escrow.service.AccountTypeCategoryService;
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
public class AccountTypeCategoryServiceImpl implements AccountTypeCategoryService {

    private final AccountTypeCategoryRepository repository;

    private final AccountTypeCategoryMapper mapper;


    @Override
    public Page<AccountTypeCategoryDTO> getAllAccountTypeCategory(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<AccountTypeCategory> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<AccountTypeCategoryDTO> getAccountTypeCategoryById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public AccountTypeCategoryDTO saveAccountTypeCategory(AccountTypeCategoryDTO accountTypeCategoryDTO) {
        log.info("Saving new application language code");
        AccountTypeCategory entity = mapper.toEntity(accountTypeCategoryDTO);
        AccountTypeCategory saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public AccountTypeCategoryDTO updateAccountTypeCategory(Long id, AccountTypeCategoryDTO accountTypeCategoryDTO) {
        log.info("Updating application language code with ID: {}", id);

        AccountTypeCategory existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AccountTypeCategory toUpdate = mapper.toEntity(accountTypeCategoryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AccountTypeCategory updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteAccountTypeCategoryById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteAccountTypeCategoryById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
