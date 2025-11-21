package com.cdl.escrow.controller;


import com.cdl.escrow.criteriaservice.AccountPurposeCriteriaService;
import com.cdl.escrow.dto.AccountPurposeDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.AccountPurposeRepository;
import com.cdl.escrow.service.AccountPurposeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/account-purpose")
@RequiredArgsConstructor
@Slf4j
public class AccountPurposeController {
    private final AccountPurposeService accountPurposeService;

   // private final AccountPurposeCriteriaService accountPurposeCriteriaService;

    private final AccountPurposeRepository repository;

    private static final String ENTITY_NAME = "ACCOUNT-PURPOSE";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurposes(
            @ParameterObject   @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all account purpose, page: {}", pageable.getPageNumber());
        Page<AccountPurposeDTO> page = accountPurposeService.getAllAccountPurpose(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AccountPurposeDTO> saveAccountPurpose(
            @Valid @RequestBody AccountPurposeDTO dto) {
        log.info("Creating new account purpose");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new account purpose cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountPurposeDTO saved = accountPurposeService.saveAccountPurpose(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountPurposeDTO> getAccountPurposeById(@PathVariable Long id) {
        log.info("Fetching ccount purpose with ID: {}", id);
        return accountPurposeService.getAccountPurposeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Account purpose not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountPurposeDTO> updateAccountPurpose(
            @PathVariable Long id,
            @Valid @RequestBody AccountPurposeDTO dto) {
        log.info("Updating account purpose with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AccountPurposeDTO updated = accountPurposeService.updateAccountPurpose(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountPurposeById(@PathVariable Long id) {
        log.info("Deleting account purpose with ID: {}", id);
        boolean deleted = accountPurposeService.deleteAccountPurposeById(id);
        if (deleted) {
            return ResponseEntity.ok("AppLanguageCode deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AppLanguageCode deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAccountPurposeById(@PathVariable Long id) {
        log.info("Soft deleting account purpose with ID: {}", id);

        boolean deleted = accountPurposeService.softDeleteAccountPurposeById(id);
        if (deleted) {
            return ResponseEntity.ok("AccountPurpose soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AccountPurpose soft deletion failed - ID: " + id);
        }
    }

}
