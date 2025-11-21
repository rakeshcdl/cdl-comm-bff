package com.cdl.escrow.service;

import com.cdl.escrow.dto.AccountPurposeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountPurposeService {

    Page<AccountPurposeDTO> getAllAccountPurpose(final Pageable pageable);

    Optional<AccountPurposeDTO> getAccountPurposeById(Long id);

    AccountPurposeDTO saveAccountPurpose(AccountPurposeDTO accountPurposeDTO);

    AccountPurposeDTO updateAccountPurpose(Long id, AccountPurposeDTO accountPurposeDTO);

    Boolean deleteAccountPurposeById(Long id);

    boolean softDeleteAccountPurposeById(Long id);
}
