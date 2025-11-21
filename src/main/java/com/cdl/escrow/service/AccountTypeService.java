package com.cdl.escrow.service;

import com.cdl.escrow.dto.AccountTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountTypeService {

    Page<AccountTypeDTO> getAllAccountType(final Pageable pageable);

    Optional<AccountTypeDTO> getAccountTypeById(Long id);

    AccountTypeDTO saveAccountType(AccountTypeDTO accountTypeDTO);

    AccountTypeDTO updateAccountType(Long id, AccountTypeDTO accountTypeDTO);

    Boolean deleteAccountTypeById(Long id);

    boolean softDeleteAccountTypeById(Long id);
}
