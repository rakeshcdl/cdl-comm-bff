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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountTypeCategoryServiceImpl implements AccountTypeCategoryService {

    private final AccountTypeCategoryRepository repository;

    private final AccountTypeCategoryMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<AccountTypeCategoryDTO> getAllAccountTypeCategory(Pageable pageable) {
        log.debug("Fetching all account type category, page: {}", pageable.getPageNumber());
        Page<AccountTypeCategory> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountTypeCategoryDTO> getAccountTypeCategoryById(Long id) {
        log.debug("Fetching account type category with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public AccountTypeCategoryDTO saveAccountTypeCategory(AccountTypeCategoryDTO accountTypeCategoryDTO) {
        log.info("Saving new account type category");
        AccountTypeCategory entity = mapper.toEntity(accountTypeCategoryDTO);
        AccountTypeCategory saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public AccountTypeCategoryDTO updateAccountTypeCategory(Long id, AccountTypeCategoryDTO accountTypeCategoryDTO) {
        log.info("Updating account type category with ID: {}", id);

        AccountTypeCategory existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("account type category not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AccountTypeCategory toUpdate = mapper.toEntity(accountTypeCategoryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AccountTypeCategory updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteAccountTypeCategoryById(Long id) {
        log.info("Deleting account type category with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("account type category not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteAccountTypeCategoryById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
