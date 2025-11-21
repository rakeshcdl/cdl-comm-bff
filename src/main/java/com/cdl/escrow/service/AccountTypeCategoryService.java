package com.cdl.escrow.service;

import com.cdl.escrow.dto.AccountTypeCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountTypeCategoryService {

    Page<AccountTypeCategoryDTO> getAllAccountTypeCategory(final Pageable pageable);

    Optional<AccountTypeCategoryDTO> getAccountTypeCategoryById(Long id);

    AccountTypeCategoryDTO saveAccountTypeCategory(AccountTypeCategoryDTO accountTypeCategoryDTO);

    AccountTypeCategoryDTO updateAccountTypeCategory(Long id, AccountTypeCategoryDTO accountTypeCategoryDTO);

    Boolean deleteAccountTypeCategoryById(Long id);

    boolean softDeleteAccountTypeCategoryById(Long id);
}
