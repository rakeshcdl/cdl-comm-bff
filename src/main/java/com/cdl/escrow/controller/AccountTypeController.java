package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.AccountTypeCriteriaService;
import com.cdl.escrow.dto.AccountTypeDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.AccountTypeRepository;
import com.cdl.escrow.service.AccountTypeService;
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
@RequestMapping("/api/v1/account-type")
@RequiredArgsConstructor
@Slf4j
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

   // private final AccountTypeCriteriaService accountTypeCriteriaService;

    private final AccountTypeRepository repository;

    private static final String ENTITY_NAME = "ACCOUNT-TYPE";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<AccountTypeDTO>> getAllAccountType(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all account type , page: {}", pageable.getPageNumber());
        Page<AccountTypeDTO> page = accountTypeService.getAllAccountType(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AccountTypeDTO> saveAccountType(
            @Valid @RequestBody AccountTypeDTO dto) {
        log.info("Creating new account type ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new account type  cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountTypeDTO saved = accountTypeService.saveAccountType(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTypeDTO> getAccountTypeById(@PathVariable Long id) {
        log.info("Fetching account type  with ID: {}", id);
        return accountTypeService.getAccountTypeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Account type not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountTypeDTO> updateAccountType(
            @PathVariable Long id,
            @Valid @RequestBody AccountTypeDTO dto) {
        log.info("Updating account type with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AccountTypeDTO updated = accountTypeService.updateAccountType(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountTypeById(@PathVariable Long id) {
        log.info("Deleting account type with ID: {}", id);
        boolean deleted = accountTypeService.deleteAccountTypeById(id);
        if (deleted) {
            return ResponseEntity.ok("AccountType deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AccountType deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAccountTypeById(@PathVariable Long id) {
        log.info("Soft deleting account type with ID: {}", id);

        boolean deleted = accountTypeService.softDeleteAccountTypeById(id);
        if (deleted) {
            return ResponseEntity.ok("AccountType soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AccountType soft deletion failed - ID: " + id);
        }
    }
}
