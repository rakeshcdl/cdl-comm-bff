package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AccountTypeDTO;
import com.cdl.escrow.entity.AccountType;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AccountTypeMapper;
import com.cdl.escrow.repository.AccountTypeRepository;
import com.cdl.escrow.service.AccountTypeService;
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
public class AccountTypeServiceImpl implements AccountTypeService {


    private final AccountTypeRepository repository;

    private final AccountTypeMapper mapper;


    @Override
    public Page<AccountTypeDTO> getAllAccountType(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<AccountType> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<AccountTypeDTO> getAccountTypeById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public AccountTypeDTO saveAccountType(AccountTypeDTO accountTypeDTO) {
        log.info("Saving new application language code");
        AccountType entity = mapper.toEntity(accountTypeDTO);
        AccountType saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public AccountTypeDTO updateAccountType(Long id, AccountTypeDTO accountTypeDTO) {
        log.info("Updating application language code with ID: {}", id);

        AccountType existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AccountType toUpdate = mapper.toEntity(accountTypeDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AccountType updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteAccountTypeById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteAccountTypeById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
