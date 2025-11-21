package com.cdl.escrow.service;

import com.cdl.escrow.dto.AuthorizedSignatoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AuthorizedSignatoryService {

    Page<AuthorizedSignatoryDTO> getAllAuthorizedSignatory(final Pageable pageable);

    Optional<AuthorizedSignatoryDTO> getAuthorizedSignatoryById(Long id);

    AuthorizedSignatoryDTO saveAuthorizedSignatory(AuthorizedSignatoryDTO authorizedSignatoryDTO);

    AuthorizedSignatoryDTO updateAuthorizedSignatory(Long id, AuthorizedSignatoryDTO authorizedSignatoryDTO);

    Boolean deleteAuthorizedSignatoryById(Long id);

    boolean softDeleteAuthorizedSignatoryById(Long id);
}
